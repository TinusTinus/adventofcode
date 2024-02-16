package nl.mvdr.adventofcode.adventofcode2015.day09

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.HamiltonianCycleAlgorithm
import org.jgrapht.alg.tour.GreedyHeuristicTSP
import org.jgrapht.graph.SimpleWeightedGraph

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int {
    val graph: Graph<String, String> = SimpleWeightedGraph(String::class.java)
    for (line in lines) {
        val (edgeString, distanceString) = line.split(" = ")
        val (city0, city1) = edgeString.split(" to ")

        graph.addVertex(city0)
        graph.addVertex(city1)

        graph.addEdge(city0, city1, edgeString)
        graph.setEdgeWeight(edgeString, distanceString.toDouble())
    }

    val algorithm: HamiltonianCycleAlgorithm<String, String> = GreedyHeuristicTSP()
    val tour = algorithm.getTour(graph)

    logger.info { tour }

    return tour.weight.toInt()
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day09-2015.txt")
    logger.info { result }
}
