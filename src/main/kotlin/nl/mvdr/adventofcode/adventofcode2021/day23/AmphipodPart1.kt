package nl.mvdr.adventofcode.adventofcode2021.day23

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val startState = State(lines)
    val endState = State(Burrow.sideRooms.map { Amphipod(it.type, it.location) }.toSet())

    val graph = SimpleDirectedWeightedGraph<State, DefaultEdge>(DefaultEdge::class.java)
    graph.addVertex(startState)

    val latestStates = mutableSetOf(startState)

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

    val algorithm: ShortestPathAlgorithm<State, DefaultEdge> = DijkstraShortestPath(graph)
    val path = algorithm.getPath(startState, endState)
    return path.weight.toInt()
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day23-2021.txt")
    logger.info { result }
}
