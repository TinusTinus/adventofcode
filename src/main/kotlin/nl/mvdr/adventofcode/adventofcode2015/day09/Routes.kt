package nl.mvdr.adventofcode.adventofcode2015.day09

import nl.mvdr.adventofcode.permutations.Permutations
import org.jgrapht.Graph
import org.jgrapht.graph.GraphWalk
import org.jgrapht.graph.SimpleWeightedGraph
import kotlin.streams.asSequence

/**
 * Returns a sequence of the weights of all possible routes through the cities given in the [lines] of the input text file,
 * where each city is visited exactly once.
 */
fun getRouteWeights(lines: List<String>): Sequence<Int> = getRouteWeights(createGraph(lines))

/**
 * Creates a weighted graph of the cities given in the [lines] of the input text file.
 */
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

/**
 * Returns a sequence of the weights of all possible routes where each city in the given [graph] is visited exactly once.
 */
private fun getRouteWeights(graph: Graph<String, String>) = Permutations.generatePermutations(graph.vertexSet())
        .asSequence()
        .map { GraphWalk(graph, it, .0) }
        .map { it.edgeList }
        .map { it.sumOf(graph::getEdgeWeight) }
        .map(Double::toInt)
