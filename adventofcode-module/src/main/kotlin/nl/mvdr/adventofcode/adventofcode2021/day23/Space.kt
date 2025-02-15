package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

/**
 * An open space, where an amphipod can stop.
 */
interface Space {
    val location: Point
}