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
    val sideRoomSize = 2

    // Note that the hallway spaces in this collection only include the ones where an amphipod may stop.
    // That is, the spaces right in front of a side room are excluded.
    val hallway = (1 until 12)
        .filter { x -> AmphipodType.entries.none { it.x == x } }
        .map(::HallwaySpace).toSet()

    val sideRooms =
        (2 until 2 + sideRoomSize).flatMap { y -> AmphipodType.entries.map { type -> RoomSpace(type.x, y, type) } }
            .toSet()

    private val spaces get() = hallway + sideRooms

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