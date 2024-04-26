package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

data class Amphipod(val type: AmphipodType, val location: Point) {
    init {
        try {
            Burrow.getSpace(location)
        } catch (e: NoSuchElementException) {
            throw IllegalArgumentException("Not a valid location for an amphipod: $location", e)
        }
    }

    fun isInHallway() = location.y == 1

    fun isInSideRoom() = !isInHallway()

    fun isAtDestination() = isInSideRoom() && (Burrow.getSpace(location) as RoomSpace).type == type
}
