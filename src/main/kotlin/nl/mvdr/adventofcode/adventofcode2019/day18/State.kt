package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point

/**
 * Representation of a possible state while traversing a maze.
 * This consists of the current [position] in the maze
 * and a [keyring] containing all the keys which have been picked up so far.
 */
data class State(private val position: Point, private val keyring: Set<Key> = emptySet())
