package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

data class Amphipod(val type: AmphipodType, val location: Point) {

    fun isAtDestination(burrow: Burrow) = burrow.isInSideRoom(location) && (burrow.getSpace(location) as RoomSpace).type == type
}
