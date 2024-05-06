package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

data class Vault(private val openPassages: Set<Point>, private val doors: Map<Point, Door>, val keys: Map<Key, Point>) {

    /**
     * Creates a graph for traversing this vault, if the given [ownedKeys] are in the traveler's possession.
     */
    fun createGraph(ownedKeys: Set<Key>): Graph<Point, DefaultEdge> {
        val openDoors = doors.filter { entry -> ownedKeys.any { key -> key.opens(entry.value) } }.keys
        val accessiblePassages = openPassages + openDoors + keys.values

        val result = SimpleGraph<Point, DefaultEdge>(DefaultEdge::class.java)
        accessiblePassages.forEach(result::addVertex)
        accessiblePassages.forEach { passage ->
            passage.neighbours()
                .filter(accessiblePassages::contains)
                .forEach { neighbour -> result.addEdge(passage, neighbour) } }

        return result
    }
}
