package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph
import kotlin.math.max
import kotlin.math.min

data class State(val amphipods: Set<Amphipod>) {
    constructor(lines: Sequence<String>) : this(parseAmphipods(lines.toList()))

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
            for (nextState in state.nextStates()) {
                graph.addVertex(nextState.first)
                if (!graph.containsEdge(state, nextState.first)) {
                    val edge = graph.addEdge(state, nextState.first)
                    graph.setEdgeWeight(edge, nextState.second.toDouble())
                }
                latestStates.add(nextState.first)
            }
        }
        return graph
    }

    /**
     * Determines possible next states, after moving a single amphipod.
     * Returns pairs of the next state and the corresponding energy cost.
     */
    private fun nextStates(): Set<Pair<State, Int>> = setOf() // TODO implement!

    private fun pathIsUnobstructed(amphipod: Amphipod, target: Point): Boolean {
        val intermediateSpaces = (1 until amphipod.location.y).map { Point(amphipod.location.x, it) } + // spaces north of the starting point
                (min(amphipod.location.x, target.x) + 1 until max(amphipod.location.x, target.x)).map { Point(it, 1) } + // hallway spaces in-between
                (1 .. target.y).map { Point(target.x, it) } // target and any spaces north of the target
        return intermediateSpaces.all { location -> amphipods.none { a -> a.location == location } }
    }
}

private fun parseAmphipods(lines: List<String>) = lines.indices.flatMap { y -> lines[y].indices.map { x -> Point(x, y) } }
    .filter { lines[it.y][it.x].isLetter() }
    .map { Amphipod(parseAmphipodType(lines[it.y][it.x]), it) }
    .toSet()
