package nl.mvdr.adventofcode.adventofcode2021.day12

import io.github.oshai.kotlinlogging.KotlinLogging
import org.jgrapht.Graph
import org.jgrapht.Graphs
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

private val logger = KotlinLogging.logger{}

data class CaveSystem(val caves: Graph<Cave, DefaultEdge>) {
    constructor(lines: Sequence<String>) : this(buildGraph(lines))

    /**
     * Counts the number of paths from the [startingCave] to the end cave, given that the [visited] caves have already been visited.
     * The [canVisit] function determines, given a cave and a list of previously visited caves, whether it is allowed to visit the cave.
     */
    fun countPathsToEnd(canVisit: (Cave, List<Cave>) -> Boolean,
                        startingCave: Cave = Cave("start"),
                        visited: List<Cave> = listOf(startingCave)
                        ): Int = when (startingCave.name) {
        "end" -> {
            logger.debug { "Path found: $visited" }
            1
        }
        else -> Graphs.neighborSetOf(caves, startingCave)
            .filter { canVisit.invoke(it, visited) }
            .sumOf { countPathsToEnd(canVisit, it, visited + it) }
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
