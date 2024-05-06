package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge

import org.jgrapht.graph.SimpleDirectedWeightedGraph

/**
 * Representation of a possible state while traversing a maze.
 * This consists of the current [position] in the maze
 * and a [keyring] containing all the keys which have been picked up so far.
 */
data class State(private val position: Point, private val keyring: Set<Key> = emptySet()) {

    /**
     * Determines whether all keys have been collected from the given [vault].
     */
    private fun isEndState(vault: Vault) = keyring.containsAll(vault.keys.keys)

    /**
     * Returns a set of states reachable from this state by picking up a key from the [vault],
     * with the associated number of steps to get to the key.
     */
    private fun pickUpKey(vault: Vault): Map<State, Int> {
        val algorithm = vault.getShortestPathAlgorithm(keyring)
        val paths = algorithm.getPaths(position)
        val result = mutableMapOf<State, Int>()
        for (key in vault.keys.keys - keyring) {
            val keyPosition = vault.keys[key]!!
            val path = paths.getPath(keyPosition)
            if (path != null) {
                result[State(keyPosition, keyring + setOf(key))] = path.length
            }
        }
        return result
    }

    /**
     * Determines how many steps it takes to collect all (remaining) keys from the given [vault].
     */
    fun collectAllKeys(vault: Vault): Int {
        val graph: Graph<State, DefaultEdge> = SimpleDirectedWeightedGraph(DefaultEdge::class.java)
        graph.addVertex(this)

        val states = mutableSetOf(this)
        while (states.isNotEmpty()) {
            val state = states.first()
            states.remove(state)

            for ((nextState, steps) in state.pickUpKey(vault)) {
                if (graph.addVertex(nextState)) {
                    states.add(nextState)
                }
                graph.addEdge(state, nextState)
                graph.setEdgeWeight(state, nextState, steps.toDouble())
            }
        }

        val algorithm: ShortestPathAlgorithm<State, DefaultEdge> = DijkstraShortestPath(graph)
        val paths = algorithm.getPaths(this)

        return graph.vertexSet()
            .filter { it.isEndState(vault) }
            .map(paths::getPath)
            .minOf { it.weight }
            .toInt()
    }
}
