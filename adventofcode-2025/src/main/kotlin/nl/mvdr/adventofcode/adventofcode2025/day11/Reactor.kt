package nl.mvdr.adventofcode.adventofcode2025.day11

import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DirectedAcyclicGraph

fun <V, E> AllDirectedPaths<V, E>.countAllPaths(sourceVertex: V, targetVertex: V) = this.getAllPaths(sourceVertex, targetVertex, true, null).size.toLong()

fun createAlgorithm(lines: Sequence<String>) = AllDirectedPaths(createGraph(lines))

private fun createGraph(lines: Sequence<String>): DirectedAcyclicGraph<String, DefaultEdge> {
    val graph = DirectedAcyclicGraph<String, DefaultEdge>(DefaultEdge::class.java)

    lines.forEach { line ->
        val (inputDevice, outputDevicesString) = line.split(": ")
        val outputDevices = outputDevicesString.split(" ")

        (setOf(inputDevice) union outputDevices)
            .filterNot(graph::containsVertex)
            .forEach(graph::addVertex)

        outputDevices.forEach { outputDevice -> graph.addEdge(inputDevice, outputDevice) }
    }

    return graph
}
