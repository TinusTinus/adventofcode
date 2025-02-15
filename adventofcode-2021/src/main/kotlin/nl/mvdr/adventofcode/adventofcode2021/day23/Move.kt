package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Move(val amphipod: Amphipod, val target: Space) {
    private val distance
        get() = amphipod.space.location.y - 1 +
                abs(amphipod.space.location.x - target.location.x) +
                target.location.y - 1

    val energyCost get() = distance * amphipod.type.energyPerStep

    /**
     * Checks whether this is a valid move, given the locations of all [amphipods] and the burrow's [sideRoomSize].
     */
    fun isValid(amphipods: Set<Amphipod>, sideRoomSize: Int) =
        (isValidMoveOutOfSideRoom(amphipods, sideRoomSize) || isValidMoveToDestination(amphipods, sideRoomSize)) &&
                !pathIsObstructed(amphipods)

    private fun isValidMoveOutOfSideRoom(amphipods: Set<Amphipod>, sideRoomSize: Int) =
        amphipod.space is RoomSpace &&
                target is HallwaySpace &&
                // do not move if the amphipod is already at its destination
                !amphipod.space.isValidDestination(amphipod.type, amphipods, sideRoomSize)


    private fun isValidMoveToDestination(amphipods: Set<Amphipod>, sideRoomSize: Int) =
        target is RoomSpace &&
                !amphipod.isAtDestination() &&
                target.isValidDestination(amphipod.type, amphipods, sideRoomSize)

    /**
     * Checks whether the path for this move is occupied by any [amphipods].
     */
    private fun pathIsObstructed(amphipods: Set<Amphipod>): Boolean {
        val intermediateSpaces =
            (1 until amphipod.location.y).map { Point(amphipod.location.x, it) } + // spaces north of the starting point
                    (min(amphipod.location.x, target.location.x) + 1 until max(
                        amphipod.location.x,
                        target.location.x
                    )).map { Point(it, 1) } + // hallway spaces in-between
                    (1..target.location.y).map {
                        Point(
                            target.location.x,
                            it
                        )
                    } // target and any spaces north of the target
        return intermediateSpaces.any { location -> amphipods.any { a -> a.location == location } }
    }
}
