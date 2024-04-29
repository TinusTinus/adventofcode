package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

/**
 * A space in one of the side rooms.
 * The [type] indicates which type of amphipod has this side room as its destination.
 */
data class RoomSpace(override val location: Point, val type: AmphipodType = AmphipodType.entries.first { it.x == location.x }): Space {

    constructor(x: Int, y: Int, type: AmphipodType) : this(Point(x, y), type)

    /**
     * Checks whether this is currently a valid destination for an amphipod of the given [amphipodType],
     * given the complete set of [amphipods] and the burrow's [sideRoomSize].
     * This is only the case if it is the correct type, and if the spaces to the south in the same side room
     * are already occupied by amphipods of the correct type.
     */
    fun isValidDestination(amphipodType: AmphipodType, amphipods: Set<Amphipod>, sideRoomSize: Int) =
        type == amphipodType &&
                (location.y + 1 until 2 + sideRoomSize).all { y ->
                    amphipods.any { a -> a.location.x == location.x &&
                            a.location.y == y &&
                            a.type == type
                    }
                }
}
