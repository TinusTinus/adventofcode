package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleGraph

data class Vault(private val openPassages: Set<Point>, private val doors: Map<Point, Door>, val keys: Map<Key, Point>) {

    private val accessiblePassages = openPassages + keys.values

    private val graph: Graph<Point, DefaultEdge> = SimpleGraph(DefaultEdge::class.java)

    init {
        accessiblePassages.forEach(graph::addVertex)
        accessiblePassages.forEach { passage ->
            passage.neighbours()
                .filter(accessiblePassages::contains)
                .forEach { neighbour -> graph.addEdge(passage, neighbour) } }

        // Also add vertices for each door.
        // Do not add any edges for now: we cannot travel through the door until its key has been collected.
        doors.keys.forEach(graph::addVertex)
    }

    /**
     * Returns a shortest path algorithm for traversing this vault, if the given [ownedKeys] are in the traveler's possession.
     * Note: the returned algorithm should no longer be used after calling this method again,
     * as this method modifies the backing graph.
     */
    // This should probably be refactored to be less fragile. (See the note in the KDOc above.)
    fun createShortestPathAlgorithm(ownedKeys: Set<Key>): ShortestPathAlgorithm<Point, DefaultEdge> {
        for ((doorLocation, door) in doors) {
            for (neighbour in doorLocation.neighbours()) {
                graph.removeEdge(doorLocation, neighbour)
                if (ownedKeys.any { it.opens(door) } &&
                        (accessiblePassages.contains(neighbour) || doors.keys.contains(neighbour))) {
                    graph.addEdge(doorLocation, neighbour)
                }
            }
        }
        return DijkstraShortestPath(graph)
    }
}
