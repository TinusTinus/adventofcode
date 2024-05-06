package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph
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
    fun isEndState(vault: Vault) = keyring.containsAll(vault.keys.keys)

    /**
     * Returns a set of states reachable from this state by picking up a key from the [vault].
     */
    private fun pickUpKey(vault: Vault): Set<Pair<State, Int>> {
        val algorithm = DijkstraShortestPath(vault.createGraph(keyring))
        val result = mutableSetOf<Pair<State, Int>>()
        for (key in vault.keys.keys - keyring) {
            val keyPosition = vault.keys[key]!!
            val path = algorithm.getPath(position, vault.keys[key])
            if (path != null) {
                result.add(Pair(State(keyPosition, keyring + setOf(key)), path.length))
            }
        }
        return result
    }

    /**
     * Determines how many steps it takes to collect all (remaining) keys from the given [vault].
     */
    fun collectAllKeys(vault: Vault): Int {
        val graph = SimpleDirectedWeightedGraph<State, DefaultEdge>(DefaultEdge::class.java)
        graph.addVertex(this)

        val states = mutableSetOf(this)
        while (states.isNotEmpty()) {
            val state = states.first()
            states.remove(state)

            for ((nextState, steps) in state.pickUpKey(vault)) {
                graph.addVertex(nextState)

                graph.addEdge(state, nextState)
                graph.setEdgeWeight(state, nextState, steps.toDouble())
            }
        }

        return 0 // TODO implement
    }
}
