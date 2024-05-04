package nl.mvdr.adventofcode.adventofcode2021.day25

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Direction
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

data class Seafloor(val width: Int, val height: Int, val eastMovingHerd: Set<Point>, val southMovingHerd: Set<Point>) {
    /**
     * The number of steps until the sea cucumbers on the floor stop moving.
     */
    val stepsToStopMoving: Int get() {
        logger.debug { "Initial state: $this" }
        var steps = 1
        var previousState = this
        var state = next
        while (state != previousState) {
            logger.debug { "After $steps steps: $state" }
            previousState = state
            state = state.next
            steps++
        }
        logger.debug { "After all sea cucumbers have stopped moving: $state" }
        return steps
    }

    /**
     * The next state, after a single step.
     */
    val next: Seafloor get() = moveEastMovingHerd().moveSouthMovingHerd()

    private fun moveEastMovingHerd() = copy(eastMovingHerd = eastMovingHerd.map(this::moveEast).toSet())

    private fun moveSouthMovingHerd() = copy(southMovingHerd = southMovingHerd.map(this::moveSouth).toSet())

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

    override fun toString(): String {
        val builder = StringBuilder("Seafloor:\n")
        for (y in 0 until height) {
            for (x in 0 until width) {
                builder.append(getRepresentation(Point(x, y)))
            }
            builder.append("\n")
        }
        return builder.toString()
    }

    private fun getRepresentation(point: Point) = when {
        eastMovingHerd.contains(point) -> '>'
        southMovingHerd.contains(point) -> 'v'
        else -> '.'
    }
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
