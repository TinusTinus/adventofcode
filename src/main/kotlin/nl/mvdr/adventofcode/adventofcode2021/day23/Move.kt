package nl.mvdr.adventofcode.adventofcode2021.day23

data class Move(val amphipod: Amphipod, val target: Space) {
    private val distance: Int get() = amphipod.location.manhattanDistance(target.location)

    val energyCost: Int get() = distance * amphipod.type.energyPerStep

    fun isMovingOutOfSideRoom() = Burrow.isInSideRoom(amphipod.location) && target is HallwaySpace

    fun isMovingToDestination() = target is RoomSpace && target.type == amphipod.type
}
