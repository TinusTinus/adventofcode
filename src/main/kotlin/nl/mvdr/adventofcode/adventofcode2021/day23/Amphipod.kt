package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

data class Amphipod(val type: AmphipodType, val space: Space) {

    val location: Point get() = space.location // TODO remove?

    fun isAtDestination() = space is RoomSpace && space.type == type
}
