package nl.mvdr.adventofcode.adventofcode2021.day25

import nl.mvdr.adventofcode.point.CharacterMapper
import nl.mvdr.adventofcode.point.Direction
import nl.mvdr.adventofcode.point.Point

data class Seafloor(val width: Int, val height: Int, val eastMovingHerd: Set<Point>, val southMovingHerd: Set<Point>) {
    val stepsToStopMoving: Int get() = when (val nextState = next) {
        this -> 0
        else -> 1 + nextState.stepsToStopMoving
    }

    private val next: Seafloor get() {
        return this // TODO
    }

    private fun isOccupied(point: Point) = eastMovingHerd.contains(point) || southMovingHerd.contains(point)
}

/**
 * Parses the [lines] from the input as a representation of the seafloor.
 */
fun parseSeafloor(lines: List<String>): Seafloor {
    val width = lines.first().length
    val height = lines.size

    val map = Point.parse2DMap(lines) { c ->
        when (c) {
            '.' -> null
            else -> Direction.parse(c)
        }
    }
    val eastMovingHerd = map.filterValues { it == Direction.RIGHT }.keys
    val southMovingHerd = map.filterValues { it == Direction.DOWN }.keys

    return Seafloor(width, height, eastMovingHerd, southMovingHerd)
}
