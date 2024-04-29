package nl.mvdr.adventofcode.adventofcode2021.day23

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph

private val logger = KotlinLogging.logger{}

data class State(private val amphipods: Set<Amphipod>, private val burrow: Burrow) {

    /**
     * Parses the initial state, based on the [lines] from the puzzle input.
     */
    constructor(lines: List<String>) : this(parseAmphipods(lines), Burrow(lines.size - 3))

    /**
     * The minimum amount of energy needed to organize the amphipods from this (start) state.
     */
    val energyCost: Int get() {
        logger.debug { "Start state: $this" }

        val endState = State(burrow.sideRooms.map { Amphipod(it.type, it) }.toSet(), burrow)
        logger.debug { "End state: $endState" }

        val algorithm: ShortestPathAlgorithm<State, DefaultEdge> = DijkstraShortestPath(graph)

        val path = algorithm.getPath(this, endState)
        logger.debug { "Path:" }
        path.vertexList.forEach { logger.debug { "$it" } }

        return path.weight.toInt()
    }

    /**
     * A directed, weighted graph based on this state.
     * Vertices in the resulting graph are states which can be reached from this one by moving the amphipods.
     * Each edge corresponds to a single movement, with the corresponding edge weight being the movement's energy cost.
     */
    private val graph: Graph<State, DefaultEdge> get() {
        val result = SimpleDirectedWeightedGraph<State, DefaultEdge>(DefaultEdge::class.java)
        result.addVertex(this)

        val latestStates = mutableSetOf(this)

        while (latestStates.isNotEmpty()) {
            val state = latestStates.first()
            latestStates.remove(state)
            for (move in state.moves) {
                val nextState = state.nextState(move)
                if (result.addVertex(nextState)) {
                    latestStates.add(nextState)
                }
                if (!result.containsEdge(state, nextState)) {
                    val edge = result.addEdge(state, nextState)
                    result.setEdgeWeight(edge, move.energyCost.toDouble())
                }

            }
        }
        return result
    }

    /**
     * Possible next moves to take, starting from this state.
     */
    private val moves: Set<Move> get() =
        // Note: if any amphipod can move into its destination in a side room, that is always the optimal thing to do.
        // It will need to do this eventually anyway, and this gets it out of the way for other moves.
        // There is no need to investigate other moves if such a move is available.
        getMovesTo(burrow.sideRooms)
            .take(1)
            .ifEmpty { getMovesTo(burrow.hallway) }
            .toSet()

    /**
     * Returns a sequence of all valid moves to one of the given [spaces].
     */
    private fun getMovesTo(spaces: Set<Space>) = amphipods.asSequence()
        .flatMap { a -> spaces.map { space -> Move(a, space) } }
        .filter { it.isValid(amphipods, burrow.sideRoomSize) }

    /**
     * Returns a pair consisting of the next state after executing the given [move], and the associated energy cost.
     */
    private fun nextState(move: Move): State {
        val newAmphipods = amphipods.toMutableSet()
        newAmphipods.remove(move.amphipod)
        newAmphipods.add(Amphipod(move.amphipod.type, move.target))
        return State(newAmphipods, burrow)
    }

    override fun toString(): String {
        val result = StringBuilder("State:\n")

        // North wall
        result.append("#############\n")

        // Hallway
        result.append("#")
        (1 until 12).map { Point(it, 1) }
            .map { hallwayLocation -> printSpace(hallwayLocation) }
            .forEach(result::append)
        result.append("#\n")

        // Side rooms
        for (y in 2 until 2 + burrow.sideRoomSize) {
            result.append("###")
            result.append(printSpace(Point(3, y)))
            result.append("#")
            result.append(printSpace(Point(5, y)))
            result.append("#")
            result.append(printSpace(Point(7, y)))
            result.append("#")
            result.append(printSpace(Point(9, y)))
            result.append("###\n")
        }
        result.append("  #########  ")
        return result.toString()
    }

    /**
     * Returns a single-character representation of the given [space] for inclusion in the result of [toString].
     * If the location is occupied by an amphipod, this method returns the single-letter representation of its type.
     * Otherwise it returns '.' to indicate an empty space.
     */
    private fun printSpace(space: Point) =
        amphipods.filter { it.location == space }.map { it.type.representation }.firstOrNull() ?: '.'
}

private fun parseAmphipods(lines: List<String>) = lines.indices.flatMap { y -> lines[y].indices.map { x -> Point(x, y) } }
    .filter { lines[it.y][it.x].isLetter() }
    .map { Amphipod(parseAmphipodType(lines[it.y][it.x]), RoomSpace(it)) }
    .toSet()

private fun parseAmphipodType(representation: Char) = AmphipodType.entries.first { it.representation == representation }
