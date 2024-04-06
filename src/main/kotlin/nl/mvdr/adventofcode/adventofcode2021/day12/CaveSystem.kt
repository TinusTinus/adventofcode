package nl.mvdr.adventofcode.adventofcode2021.day12

import org.jgrapht.Graph
import org.jgrapht.Graphs
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

data class CaveSystem(val caves: Graph<Cave, DefaultEdge>) {
    constructor(lines: Sequence<String>) : this(buildGraph(lines))

    fun countPathsToEnd(startingCave: Cave = Cave("start"), visited: List<Cave> = listOf(startingCave)): Int = when (startingCave) {
        Cave("end") -> 1
        else -> Graphs.neighborSetOf(caves, startingCave)
            .filter { neighbour -> neighbour.big || !visited.contains(neighbour) }
            .sumOf { countPathsToEnd(it, visited + it) }
    }
}

/**
 * Builds a graph of caves, based on the [lines] from the puzzle input.
 */
private fun buildGraph(lines: Sequence<String>): Graph<Cave, DefaultEdge> {
    val result = SimpleGraph<Cave, DefaultEdge>(DefaultEdge::class.java)
    for (line in lines) {
        val (from, to) = line.split("-")
            .map(::Cave)
            .onEach(result::addVertex)
        result.addEdge(from, to)
    }
    return result
}
