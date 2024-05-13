package nl.mvdr.adventofcode.adventofcode2020.day24

import nl.mvdr.adventofcode.point.Point

data class Path(private val steps: List<Direction>) {
    /**
     * Constructs a path based on its [text] representation.
     * For example: "nwwswee".
     */
    constructor(text: String) : this(parseSteps(text))

    fun move(startingPoint: Point = Point.ORIGIN) = steps.fold(startingPoint) { point, step -> step.move(point)}
}

/**
 * Parses the [text] representation of a path as a list of steps.
 * For example: "nwwswee".
 */
private fun parseSteps(text: String): List<Direction> = when (text) {
    "" -> emptyList()
    else -> {
        val firstDirection = Direction.entries.first { text.startsWith(it.representation) }
        val remainingText = text.drop(firstDirection.representation.length)
        listOf(firstDirection) + parseSteps(remainingText)
    }
}