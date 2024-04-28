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
    private val moves: Set<Move> get() {
        // Note: if an amphipod can move to one of its destination spaces, that is always the best thing to do.
        // It will need to do this eventually anyway, and this gets it out of the way for other moves.
        // If there are multiple moves to destination: just pick one.
        val moveToDestination = amphipods.asSequence()
            .flatMap { a -> Burrow.sideRooms.map { space -> Move(a, space) } }
            .filter(this::isValid)
            .firstOrNull()

        val result: Set<Move>
        if (moveToDestination == null) {
            result = amphipods.flatMap { a -> Burrow.hallway.map { space -> Move(a, space) } }
                .filter(this::isValid).toSet()
        } else {
            result = setOf(moveToDestination)
        }

        return result
    }

    fun isEndState() = amphipods.all(Amphipod::isAtDestination)

    /**
     * Determines the minimum amount of energy needed to organize the amphipods from this (start) state.
     */
    fun computeEnergyCost(): Int {
        val graph = createGraph()
        logger.info { "graph contains " + graph.vertexSet().size + " vertices, " + graph.edgeSet().size + " edges" } // TODO clean up logs
        val algorithm: ShortestPathAlgorithm<State, DefaultEdge> = DijkstraShortestPath(graph)
        val endState = State(Burrow.sideRooms.map { Amphipod(it.type, it.location) }.toSet())
        val path = algorithm.getPath(this, endState)

        // TODO clean up logging
        logger.info { "Path:" }
        path.vertexList.forEach { logger.info { "$it" } }

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
        if (newState.isEndState()) {
            // logger.info { "End state found: $newState" } // TODO clean up logging
        }
        return Pair(newState, move.energyCost)
    }

    private fun isValid(move: Move): Boolean {
        val isValidMoveOutOfSideRoom = move.isMovingOutOfSideRoom() &&
                !isValidDestination(Burrow.getSpace(move.amphipod.location) as RoomSpace, move.amphipod.type)
        val isValidMoveToDestination = move.isMovingToDestination() && destinationIsAvailable(move)
        if (isValidMoveToDestination && !pathIsObstructed(move)) {
//            logger.info { "Moving to destination: $move" } // TODO clean up logging
        }
        if (isValidMoveToDestination && !pathIsObstructed(move) && move.target.location.y == 3) {
//            logger.info { "Moving to southern end of a room: $move" } // TODO clean up logging
        }
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
    private fun destinationIsAvailable(move: Move) = isValidDestination(move.target as RoomSpace, move.amphipod.type)

    /**
     * Checks whether the given [roomSpace] is currently a valid destination for an amphipod of the given [type].
     * This is only the case if it is the south side of the side room,
     * or if the south side already contains another amphipod of the same type.
     */
    // TODO this method does not take into account that this amphipod itself may be occupying the south side of the room.
    private fun isValidDestination(roomSpace: RoomSpace, type: AmphipodType) =
        roomSpace.type == type &&
            (roomSpace.location.y + 1 .. 3).all { y ->
                amphipods.any { a ->
                    a.location == Point(roomSpace.location.x, y) && a.type == type
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
        for (y in 2 until 4) {
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
    .map { Amphipod(parseAmphipodType(lines[it.y][it.x]), it) }
    .toSet()

private fun parseAmphipodType(representation: Char) = AmphipodType.entries.first { it.representation == representation }
