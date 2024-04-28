package nl.mvdr.adventofcode.adventofcode2021.day23

import kotlin.math.abs

data class Move(val amphipod: Amphipod, val target: Space) {
    private val distance: Int get() = amphipod.location.y - 1 +
            abs(amphipod.location.x - target.location.x) +
            target.location.y - 1

    val energyCost: Int get() = distance * amphipod.type.energyPerStep

    fun isMovingOutOfSideRoom(burrow: Burrow) = burrow.isInSideRoom(amphipod.location) && target is HallwaySpace

    fun isMovingToDestination() = target is RoomSpace && target.type == amphipod.type
}
