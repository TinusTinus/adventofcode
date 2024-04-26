package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.point.Point

/**
 * Representation of a burrow, of the following form:
 * ```
 * #############
 * #...........#
 * ###.#.#.#.###
 *   #.#.#.#.#
 *   #########
 * ```
 */
object Burrow {

    val sideRooms = setOf(
        RoomSpace(3, 2, AmphipodType.AMBER),
        RoomSpace(3, 3, AmphipodType.AMBER),
        RoomSpace(5, 2, AmphipodType.BRONZE),
        RoomSpace(5, 3, AmphipodType.BRONZE),
        RoomSpace(7, 2, AmphipodType.COPPER),
        RoomSpace(7, 3, AmphipodType.COPPER),
        RoomSpace(9, 2, AmphipodType.DESERT),
        RoomSpace(9, 3, AmphipodType.DESERT)
    )

    // Note that the hallway spaces in this collection only include the ones where an amphipod may stop.
    // That is, the spaces right in front of a side room are not included.
    val hallway = setOf(
        HallwaySpace(1),
        HallwaySpace(2),
        HallwaySpace(4),
        HallwaySpace(6),
        HallwaySpace(8),
        HallwaySpace(10),
        HallwaySpace(11)
    )

    val spaces get() = hallway + sideRooms

    fun getSpace(location: Point) = spaces.first { it.location == location }

    /**
     * Checks whether the given [location] is in a hallway space.
     * Note that the given location must be a valid space, within either the hallway or a side room.
     */
    fun isInHallway(location: Point) = location.y == 1

    /**
     * Checks whether the given [location] is in a side room space.
     * Note that the given location must be a valid space, within either the hallway or a side room.
     */
    fun isInSideRoom(location: Point) = !isInHallway(location)
}