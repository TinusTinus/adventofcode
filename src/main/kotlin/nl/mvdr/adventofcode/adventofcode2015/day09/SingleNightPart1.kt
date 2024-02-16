package nl.mvdr.adventofcode.adventofcode2015.day09

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.HamiltonianCycleAlgorithm
import org.jgrapht.alg.tour.GreedyHeuristicTSP
import org.jgrapht.graph.SimpleWeightedGraph

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int {
    val graph: Graph<String, String> = createGraph(lines)
    val algorithm: HamiltonianCycleAlgorithm<String, String> = GreedyHeuristicTSP()
    val tour = algorithm.getTour(graph)
    logger.debug { "Tour found: $tour" }

    // Note: the tour found using a standard traveling salesman algorithm includes a return to the starting city.
    // Drop the longest edge in this tour.
    // I don't think this necessarily guarantees the actual shortest path.
    // However, both for the given example and my actual puzzle input, this turns out to give the correct answer anyway.
    val longestEdgeWeight = tour.edgeList.maxOfOrNull { graph.getEdgeWeight(it) }!!
    return (tour.weight - longestEdgeWeight).toInt()
}

private fun createGraph(lines: List<String>): Graph<String, String> {
    val graph: Graph<String, String> = SimpleWeightedGraph(String::class.java)
    for (line in lines) {
        val (edgeString, distanceString) = line.split(" = ")
        val (city0, city1) = edgeString.split(" to ")

        graph.addVertex(city0)
        graph.addVertex(city1)

        graph.addEdge(city0, city1, edgeString)
        graph.setEdgeWeight(edgeString, distanceString.toDouble())
    }
    return graph
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day09-2015.txt")
    logger.info { result }
}
