package nl.mvdr.adventofcode.adventofcode2015.day18

import nl.mvdr.adventofcode.point.Point

/**
 * A grid of [lights].
 */
data class Grid(val lights: Map<Point, Light>) {
    /**
     * Performs the requested number of [steps] in the animation.
     */
    fun step(steps: Int): Grid = when {
        steps < 0 -> throw IllegalArgumentException("Steps must be positive, was $steps")
        steps == 0 -> this
        else -> step().step(steps - 1)
    }

    /**
     * Performs a single step in the animation.
     */
    private fun step(): Grid = Grid(lights.keys.associateWith(this::nextState))

    /**
     * Determines the next state for the light at the given [location].
     * A light which is on stays on when 2 or 3 neighbors are on, and turns off otherwise.
     * A light which is off turns on if exactly 3 neighbors are on, and stays off otherwise.
     */
    private fun nextState(location: Point): Light = when(lights[location]!!) {
        Light.ON -> when(countLitNeighbours(location)) {
            2, 3 -> Light.ON
            else -> Light.OFF
        }
        Light.OFF -> when(countLitNeighbours(location)) {
            3 -> Light.ON
            else -> Light.OFF
        }
    }

    /**
     * Given the [location] of a light in the grid, this function determines how many neighbouring lights are lit.
     */
    private fun countLitNeighbours(location: Point) = location.neighboursIncludingDiagonals().map { lights[it] }.count { it == Light.ON }

    /**
     * Counts the number of lights which are currently on.
     */
    fun count() = lights.values.count { it == Light.ON}
}

/**
 * Parses the [lines] of the puzzle input as a grid of lights.
 */
fun parseGrid(lines: Sequence<String>) = Grid(Point.parse2DMap(lines.toList(), ::parseLight))