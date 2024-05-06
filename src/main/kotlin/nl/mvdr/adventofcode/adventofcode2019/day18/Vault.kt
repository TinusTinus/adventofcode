package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

data class Vault(private val openPassages: Set<Point>, private val doors: Map<Point, Door>, val keys: Map<Key, Point>) {
    private val algorithmCache: MutableMap<Set<Key>, ShortestPathAlgorithm<Point, DefaultEdge>> = mutableMapOf()

    /**
     * Returns a shortest path algorithm for traversing this vault, if the given [ownedKeys] are in the traveler's possession.
     */
    fun getShortestPathAlgorithm(ownedKeys: Set<Key>): ShortestPathAlgorithm<Point, DefaultEdge> {
        return algorithmCache.getOrPut(ownedKeys) { createShortestPathAlgorithm(ownedKeys) }
    }

    private fun createShortestPathAlgorithm(ownedKeys: Set<Key>): ShortestPathAlgorithm<Point, DefaultEdge> {
        val openDoors = doors.filter { entry -> ownedKeys.any { key -> key.opens(entry.value) } }.keys
        val accessiblePassages = openPassages + openDoors + keys.values

        val graph = SimpleGraph<Point, DefaultEdge>(DefaultEdge::class.java)
        accessiblePassages.forEach(graph::addVertex)
        accessiblePassages.forEach { passage ->
            passage.neighbours()
                .filter(accessiblePassages::contains)
                .forEach { neighbour -> graph.addEdge(passage, neighbour) } }
        return DijkstraShortestPath(graph)
    }
}
