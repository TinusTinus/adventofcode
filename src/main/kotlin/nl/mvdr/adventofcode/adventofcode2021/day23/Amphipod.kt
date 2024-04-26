package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

data class Amphipod(val type: AmphipodType, val location: Point) {

    fun isAtDestination() = Burrow.isInSideRoom(location) && (Burrow.getSpace(location) as RoomSpace).type == type

    /**
     * Computes how much energy it would cost to move to [target].
     */
    fun computeMoveCost(target: Space) = location.manhattanDistance(target.location) * type.energyPerStep
}
