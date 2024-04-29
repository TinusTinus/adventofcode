package nl.mvdr.adventofcode.adventofcode2021.day23

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph
import kotlin.math.max
import kotlin.math.min

private val logger = KotlinLogging.logger{}

data class State(private val amphipods: Set<Amphipod>, private val burrow: Burrow) {
    constructor(lines: List<String>) : this(parseAmphipods(lines), Burrow(lines.size - 3))

    /**
     * Determines possible next states, after moving a single amphipod.
     * Returns pairs of the next state and the corresponding energy cost.
     */
    private val nextStates get() = moves.map(this::nextState).toSet()

    /**
     * Determines possible moves, based on this starting state.
     */
    private val moves: Set<Move> get() {
        // Note: if an amphipod can move to one of its destination spaces, that is always the best thing to do.
        // It will need to do this eventually anyway, and this gets it out of the way for other moves.
        // If there are multiple moves to destination: just pick one.
        val moveToDestination = amphipods.asSequence()
            .flatMap { a -> burrow.sideRooms.map { space -> Move(a, space) } }
            .firstOrNull(this::isValid)

        val result: Set<Move>
        if (moveToDestination == null) {
            result = amphipods.asSequence()
                .flatMap { a -> burrow.hallway.map { space -> Move(a, space) } }
                .filter(this::isValid)
                .toSet()
        } else {
            result = setOf(moveToDestination)
        }
        return result
    }

    /**
     * Determines the minimum amount of energy needed to organize the amphipods from this (start) state.
     */
    val energyCost: Int get() {
        val algorithm: ShortestPathAlgorithm<State, DefaultEdge> = DijkstraShortestPath(graph)
        logger.debug { "Start state: $this" }
        val endState = State(burrow.sideRooms.map { Amphipod(it.type, it) }.toSet(), burrow)
        logger.debug { "End state: $endState" }
        val path = algorithm.getPath(this, endState)

        logger.debug { "Path:" }
        path.vertexList.forEach { logger.debug { "$it" } }

        return path.weight.toInt()
    }

    /**
     * Creates a directed, weighted graph based on this state.
     * Vertices in the resulting graph are states which can be reached from this one by moving the amphipods.
     * Edges are single movements, with the corresponding edge weight being the movement's energy cost.
     */
    private val graph: Graph<State, DefaultEdge> get() {
        val graph = SimpleDirectedWeightedGraph<State, DefaultEdge>(DefaultEdge::class.java)
        graph.addVertex(this)

        val latestStates = mutableSetOf(this)

        while (latestStates.isNotEmpty()) {
            val state = latestStates.first()
            latestStates.remove(state)
            for (nextState in state.nextStates) {
                if (graph.addVertex(nextState.first)) {
                    latestStates.add(nextState.first)
                }
                if (!graph.containsEdge(state, nextState.first)) {
                    val edge = graph.addEdge(state, nextState.first)
                    graph.setEdgeWeight(edge, nextState.second.toDouble())
                }

            }
        }
        return graph
    }


    /**
     * Returns a pair consisting of the next state after executing the given [move], and the associated energy cost.
     */
    private fun nextState(move: Move): Pair<State, Int> {
        val newAmphipods = amphipods.toMutableSet()
        newAmphipods.remove(move.amphipod)
        newAmphipods.add(Amphipod(move.amphipod.type, move.target))
        val newState = State(newAmphipods, burrow)
        return Pair(newState, move.energyCost)
    }

    private fun isValid(move: Move): Boolean {
        val isValidMoveOutOfSideRoom = move.isMovingOutOfSideRoom() &&
                !isValidDestination(move.amphipod.space as RoomSpace, move.amphipod.type)
        val isValidMoveToDestination = move.isMovingToDestination() && destinationIsAvailable(move)
        return (isValidMoveOutOfSideRoom || isValidMoveToDestination) && !pathIsObstructed(move)
    }

    /**
     * Checks whether the path for the given [move] is not occupied by any other amphipods.
     */
    private fun pathIsObstructed(move: Move) = pathIsObstructed(move.amphipod, move.target.location)

    /**
     * Checks whether the path for the given [amphipod] to the given [target] is not occupied by any amphipods (including itself!).
     */
    private fun pathIsObstructed(amphipod: Amphipod, target: Point): Boolean {
        val intermediateSpaces = (1 until amphipod.location.y).map { Point(amphipod.location.x, it) } + // spaces north of the starting point
                (min(amphipod.location.x, target.x) + 1 until max(amphipod.location.x, target.x)).map { Point(it, 1) } + // hallway spaces in-between
                (1 .. target.y).map { Point(target.x, it) } // target and any spaces north of the target
        return intermediateSpaces.any { location -> amphipods.any { a -> a.location == location } }
    }

    /**
     * Checks that it is allowed to move to a side room as specified in [move].
     * This is only allowed if it is the south side of the side room,
     * or if the south side already contains another amphipod of the same type.
     */
    private fun destinationIsAvailable(move: Move) = !move.amphipod.isAtDestination() && isValidDestination(move.target as RoomSpace, move.amphipod.type)

    /**
     * Checks whether the given [roomSpace] is currently a valid destination for an amphipod of the given [type].
     * This is only the case if it is the south side of the side room,
     * or if the south side already contains another amphipod of the same type.
     */
    private fun isValidDestination(roomSpace: RoomSpace, type: AmphipodType) =
        roomSpace.type == type &&
            (roomSpace.location.y + 1 until 2 + burrow.sideRoomSize).all { y ->
                amphipods.any { a ->
                    a.space == RoomSpace(roomSpace.location.x, y, a.type)
                }
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
