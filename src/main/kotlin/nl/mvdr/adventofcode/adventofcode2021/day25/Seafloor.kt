package nl.mvdr.adventofcode.adventofcode2021.day25

import nl.mvdr.adventofcode.point.Direction
import nl.mvdr.adventofcode.point.Point

data class Seafloor(val width: Int, val height: Int, val eastMovingHerd: Set<Point>, val southMovingHerd: Set<Point>) {
    /**
     * The number of steps until the sea cucumbers on the floor stop moving.
     */
    val stepsToStopMoving: Int get() {
        var steps = 0
        var previousState = this
        var state = next
        while (state != previousState) {
            previousState = state
            state = state.next
            steps++
        }
        return steps + 1
    }

    /**
     * The next state, after taking a single step.
     */
    val next: Seafloor get() {
        val newEastMovingHerd = eastMovingHerd.map(this::moveEast).toSet()
        val newSouthMovingHerd = southMovingHerd.map(this::moveSouth).toSet()
        return copy(eastMovingHerd = newEastMovingHerd, southMovingHerd = newSouthMovingHerd)
    }

    private fun moveEast(point: Point) = moveIfPossible(point, Point((point.x + 1) % width, point.y))

    private fun moveSouth(point: Point) = moveIfPossible(point, Point(point.x, (point.y + 1) % height))

    /**
     * Checks whether it is possible for a sea cucumber to move from its [currentLocation] to the [newLocation].
     * Returns the new location if the move is possible, and the current location if it is not.
     */
    private fun moveIfPossible(currentLocation: Point, newLocation: Point) = when {
        isOccupied(newLocation) -> currentLocation
        else -> newLocation
    }

    /**
     * Checks whether the given [point] is occupied by a sea cucumber.
     */
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
