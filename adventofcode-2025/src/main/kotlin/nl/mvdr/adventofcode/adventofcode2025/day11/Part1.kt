package nl.mvdr.adventofcode.adventofcode2025.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver
import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DirectedAcyclicGraph

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val graph = DirectedAcyclicGraph<String, DefaultEdge>(DefaultEdge::class.java)

    lines.forEach { line ->
        val (inputDevice, outputDevicesString) = line.split(": ")
        val outputDevices = outputDevicesString.split(" ")

        (setOf(inputDevice) union outputDevices)
            .filterNot(graph::containsVertex)
            .forEach(graph::addVertex)

        outputDevices.forEach { outputDevice -> graph.addEdge(inputDevice, outputDevice) }
    }

    val algorithm = AllDirectedPaths(graph)
    return algorithm.getAllPaths("you", "out", true, null).size
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day11.txt")
    logger.info { result }
}
