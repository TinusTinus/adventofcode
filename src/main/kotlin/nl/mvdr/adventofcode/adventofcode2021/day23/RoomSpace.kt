package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

/**
 * A space in one of the side rooms.
 * The [type] indicates which type of amphipod has this side room as its destination.
 */
data class RoomSpace(override val location: Point, val type: AmphipodType = AmphipodType.entries.first { it.x == location.x }): Space {

    constructor(x: Int, y: Int, type: AmphipodType) : this(Point(x, y), type)
}
