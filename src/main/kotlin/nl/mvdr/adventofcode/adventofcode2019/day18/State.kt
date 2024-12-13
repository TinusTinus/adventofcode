package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.point.Point

/**
 * Representation of a possible state while traversing a maze.
 * This consists of the current [positions] of the traveler(s) in the maze
 * and a [keyring] containing all the keys which have been picked up so far.
 */
data class State(val positions: Set<Point>, val keyring: Set<Key> = emptySet())
