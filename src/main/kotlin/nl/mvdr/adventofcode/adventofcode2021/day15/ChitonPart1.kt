package nl.mvdr.adventofcode.adventofcode2021.day15

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.*

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val risks = Point.parse2DMap(lines.toList()) { char -> char.toString().toInt() }

    val graph = createGraph(risks)

    val start = Point(0, 0)
    val finish = Point(Point.maxX(risks.keys), Point.maxY(risks.keys))

    val path = DijkstraShortestPath.findPathBetween(graph, start, finish)

    return path.weight.toInt()
}

private fun createGraph(risks: MutableMap<Point, Int>): Graph<Point, DefaultEdge> {
    val graph = SimpleDirectedWeightedGraph<Point, DefaultEdge>(DefaultEdge::class.java)

    // Add vertices
    risks.keys.forEach(graph::addVertex)

    // Add edges
    risks.keys.forEach { point -> point
        .neighbours()
        .filter(graph::containsVertex)
        .forEach { neighbour -> createEdge(graph, point, neighbour, risks) }
    }

    return graph
}

private fun createEdge(graph: SimpleDirectedGraph<Point, DefaultEdge>, source: Point, target: Point, risks: MutableMap<Point, Int>) {
    val edge = graph.addEdge(source, target)
    graph.setEdgeWeight(edge, risks[target]!!.toDouble())
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day15-2021.txt")
    logger.info { result }
}
