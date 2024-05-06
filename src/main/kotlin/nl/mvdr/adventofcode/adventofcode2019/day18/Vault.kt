package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

data class Vault(private val openPassages: Set<Point>, private val doors: Map<Point, Door>, val keys: Map<Key, Point>) {
    /**
     * A graph representing this vault, assuming that all doors are closed.
     */
    private val graph: Graph<Point, DefaultEdge> = SimpleGraph(DefaultEdge::class.java)

    init {
        openPassages.forEach(graph::addVertex)
        openPassages.forEach { passage ->
            passage.neighbours()
                .filter(openPassages::contains)
                .forEach { neighbour -> graph.addEdge(passage, neighbour) } }
    }
}
