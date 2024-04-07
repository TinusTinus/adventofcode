package nl.mvdr.adventofcode.adventofcode2021.day13

import nl.mvdr.adventofcode.point.Point

data class FoldInstruction(val axis: Axis, val index: Int) {
    fun perform(dots: Set<Point>) = dots.map(this::fold).toSet()

    private fun fold(point: Point): Point {
        return point // TODO implement!
    }
}

private const val PREFIX = "fold along "

/**
 * Parses the given [text] representation of a folding instruction.
 * For example: "fold along y=7"
 */
fun parseFoldInstruction(text: String): FoldInstruction {
    if (!text.startsWith(PREFIX)) {
        throw IllegalArgumentException("Unable to parse as a folding instruction: '$text'")
    }
    val (axisString, indexString) = text.drop(PREFIX.length).split("=")
    return FoldInstruction(parseAxis(axisString), indexString.toInt())
}
