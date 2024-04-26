package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

/**
 * A space in the hallway, where an amphipod can stop.
 */
data class HallwaySpace(override val location: Point): Space {
    constructor(x: Int) : this(Point(x, 1))
}
