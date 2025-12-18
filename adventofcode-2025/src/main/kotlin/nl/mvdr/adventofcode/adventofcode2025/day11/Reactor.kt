package nl.mvdr.adventofcode.adventofcode2025.day11

import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DirectedAcyclicGraph

fun solve(lines: Sequence<String>, start: String, requiredDevices: Set<String> = setOf()): Int {
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
    return algorithm.getAllPaths(start, "out", true, null)
        .filter { it.vertexList.containsAll(requiredDevices) }
        .size
}
