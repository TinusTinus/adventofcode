package nl.mvdr.adventofcode.adventofcode2020.day24

import nl.mvdr.adventofcode.point.Point

data class Path(private val steps: List<Direction>) {
    constructor(text: String) : this(parseSteps(text))

    fun move(startingPoint: Point = Point.ORIGIN): Point {
        var result = startingPoint
        for (step in steps) {
            result = step.move(result)
        }
        return result
    }
}

/**
 * Parses the [text] representation of a path as a list of steps.
 */
private fun parseSteps(text: String): List<Direction> = when (text) {
    "" -> emptyList()
    else -> {
        val firstDirection = Direction.entries.first { text.startsWith(it.representation) }
        val remainingText = text.drop(firstDirection.representation.length)
        listOf(firstDirection) + parseSteps(remainingText)
    }
}