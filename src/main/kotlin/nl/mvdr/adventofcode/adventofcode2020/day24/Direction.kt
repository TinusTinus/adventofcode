package nl.mvdr.adventofcode.adventofcode2020.day24

import nl.mvdr.adventofcode.point.Point

/**
 * One of six directions in a hexagonal grid.
 */
enum class Direction(val representation: String, private val movement: (Point) -> Point) {
    EAST("e", { point -> Point(point.x + 2, point.y) }),
    SOUTHEAST("se", { point -> Point(point.x + 1, point.y + 1) }),
    SOUTHWEST("sw", { point -> Point(point.x - 1, point.y + 1) }),
    WEST("w", { point -> Point(point.x - 2, point.y) }),
    NORTHWEST("nw", { point -> Point(point.x - 1, point.y - 1) }),
    NORTHEAST("ne", { point -> Point(point.x + 1, point.y - 1) });

    fun move(tile: Tile) = Tile(movement.invoke(tile.location))
}