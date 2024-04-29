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
 * The [sideRoomSize] parameter specifies how many amphipods can fit in a single side room.
 */
data class Burrow(val sideRoomSize: Int = 2) {

    // Note that the hallway spaces in this collection only include the ones where an amphipod may stop.
    // That is, the spaces right in front of a side room are excluded.
    val hallway = (1 until 12)
        .filter { x -> AmphipodType.entries.none { it.x == x } }
        .map(::HallwaySpace).toSet()

    val sideRooms =
        (2 until 2 + sideRoomSize).flatMap { y -> AmphipodType.entries.map { type -> RoomSpace(type.x, y, type) } }
            .toSet()
}