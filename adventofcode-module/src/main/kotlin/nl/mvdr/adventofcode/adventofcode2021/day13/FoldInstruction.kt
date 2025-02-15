package nl.mvdr.adventofcode.adventofcode2021.day13

import nl.mvdr.adventofcode.point.Point

data class FoldInstruction(val axis: Axis, val index: Int) {
    fun perform(dots: Set<Point>) = dots.map(this::fold).toSet()

    /**
     * Determines the new location of the given [dot] after performing this fold instruction.
     */
    private fun fold(dot: Point): Point = axis.updateValue(dot, fold(axis.getValue(dot)))

    /**
     * Determines the new location for the given [value] along this instruction's [axis].
     */
    private fun fold(value: Int) = when {
        value < index -> value
        value == index -> throw IllegalStateException("Dot found along folding axis.")
        else -> index - (value - index)
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
