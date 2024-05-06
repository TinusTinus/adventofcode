package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point

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
     * Determines how many steps it takes to collect all (remaining) keys from the given [vault].
     */
    fun collectAllKeys(vault: Vault): Int {
        val vaultGraph = vault.createGraph(keyring)



        return 0 // TODO implement
    }
}
