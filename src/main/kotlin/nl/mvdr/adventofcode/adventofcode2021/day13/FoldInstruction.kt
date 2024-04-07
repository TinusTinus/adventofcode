package nl.mvdr.adventofcode.adventofcode2021.day13

data class FoldInstruction(val axis: Axis, val index: Int)

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

