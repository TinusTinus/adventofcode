package nl.mvdr.adventofcode.adventofcode2021.day15

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
import org.jgrapht.graph.SimpleDirectedWeightedGraph

/**
 * Parses the [lines] from the puzzle input as a map containing risk values.
 */
fun parseRisks(lines: Sequence<String>): MutableMap<Point, Int> =
    Point.parse2DMap(lines.toList()) { char -> char.toString().toInt() }

/**
 * Finds the total risk of the least risky path, for the given [risks].
 */
fun solve(risks: Map<Point, Int>): Int {
    val graph = createGraph(risks)

    val start = Point(0, 0)
    val finish = Point(Point.maxX(risks.keys), Point.maxY(risks.keys))

    val path = DijkstraShortestPath.findPathBetween(graph, start, finish)

    return path.weight.toInt()
}

private fun createGraph(risks: Map<Point, Int>): Graph<Point, DefaultEdge> {
    val graph = SimpleDirectedWeightedGraph<Point, DefaultEdge>(DefaultEdge::class.java)

    // Add vertices
    risks.keys.forEach(graph::addVertex)

    // Add edges
    risks.keys.forEach { point -> point
        .neighbours()
        .filter(graph::containsVertex)
        .forEach { neighbour -> addWeightedEdge(graph, point, neighbour, risks[neighbour]!!) }
    }

    return graph
}

private fun addWeightedEdge(graph: SimpleDirectedGraph<Point, DefaultEdge>, source: Point, target: Point, risk: Int) {
    val edge = graph.addEdge(source, target)
    graph.setEdgeWeight(edge, risk.toDouble())
}