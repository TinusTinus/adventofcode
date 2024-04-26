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

data class State(val amphipods: Set<Amphipod>) {
    constructor(lines: Sequence<String>) : this(parseAmphipods(lines.toList()))

    /**
     * Determines possible next states, after moving a single amphipod.
     * Returns pairs of the next state and the corresponding energy cost.
     */
    private val nextStates get() = moves.map(this::nextState).toSet()

    /**
     * Determines possible moves, based on this starting state.
     */
    private val moves: Set<Move> get() = amphipods.flatMap { a -> Burrow.spaces.map { space -> Move(a, space) } }
            .filter(this::isValid)
            .toSet()

    /**
     * Determines the minimum amount of energy needed to organize the amphipods from this (start) state.
     */
    fun computeEnergyCost(): Int {
        val graph = createGraph()
        val algorithm: ShortestPathAlgorithm<State, DefaultEdge> = DijkstraShortestPath(graph)
        val endState = State(Burrow.sideRooms.map { Amphipod(it.type, it.location) }.toSet())
        val path = algorithm.getPath(this, endState)
        return path.weight.toInt()
    }

    /**
     * Creates a directed, weighted graph based on this state.
     * Vertices in the resulting graph are states which can be reached from this one by moving the amphipods.
     * Edges are single movements, with the corresponding edge weight being the movement's energy cost.
     */
    private fun createGraph(): Graph<State, DefaultEdge> {
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

                    // TODO clean up this logging!
//                    logger.info { "Added an edge;" }
//                    logger.info { "from: $state,"}
//                    logger.info { "to: " + nextState.first }
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
        newAmphipods.add(Amphipod(move.amphipod.type, move.target.location))
        val newState = State(newAmphipods)
        return Pair(newState, move.energyCost)
    }

    private fun isValid(move: Move): Boolean {
        val result = ((move.isMovingOutOfSideRoom() && !isValidDestination(Burrow.getSpace(move.amphipod.location) as RoomSpace, move.amphipod.type)) ||
                (move.isMovingToDestination() && destinationIsAvailable(move))) &&
                        !pathIsObstructed(move)

        // TODO clean up this logging and this method
//        if (result) {
//            logger.info { "$move from state $this is valid" }
//        }

        return result
    }

    /**
     * Checks whether the path for the given [move] is not occupied by any other amphipods.
     */
    private fun pathIsObstructed(move: Move) = pathIsObstructed(move.amphipod, move.target.location)

    /**
     * Checks whether the path for the given [amphipod] to the given [target] is not occupied by any other amphipods.
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
    private fun destinationIsAvailable(move: Move) = isValidDestination(move.target as RoomSpace, move.amphipod.type)

    /**
     * Checks whether the given [roomSpace] is currently a valid destination for an amphipod of the given [type].
     * This is only the case if it is the south side of the side room,
     * or if the south side already contains another amphipod of the same type.
     */
    private fun isValidDestination(roomSpace: RoomSpace, type: AmphipodType) =
        roomSpace.type == type &&
            (2 until roomSpace.location.y).all { y ->
                amphipods.any { a ->
                    a.location == Point(roomSpace.location.x, y) && a.type == type
                }
            }

}

private fun parseAmphipods(lines: List<String>) = lines.indices.flatMap { y -> lines[y].indices.map { x -> Point(x, y) } }
    .filter { lines[it.y][it.x].isLetter() }
    .map { Amphipod(parseAmphipodType(lines[it.y][it.x]), it) }
    .toSet()

private fun parseAmphipodType(representation: Char) = AmphipodType.entries.first { it.representation == representation }
