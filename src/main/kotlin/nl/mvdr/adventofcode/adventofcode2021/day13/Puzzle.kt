package nl.mvdr.adventofcode.adventofcode2021.day13

import nl.mvdr.adventofcode.point.Point

/**
 * A transparent piece of paper containing a puzzle,
 * with [dots] and folding [instructions].
 */
data class Puzzle(private val dots: Set<Point>, private val instructions: List<FoldInstruction>) {
    /**
     * Performs only the first fold instruction.
     */
    fun performInstruction(): Puzzle = Puzzle(instructions.first().perform(dots), instructions.drop(1))

    /**
     * Counts the number of visible dots.
     */
    fun countDots() = dots.size

    override fun toString(): String = Point.visualize(dots)
}

/**
 * Parses the textual representation of a puzzle.
 */
fun parsePuzzle(lines: List<String>): Puzzle {
    val indexOfBlankLine = lines.indexOf("")
    val dots = lines.subList(0, indexOfBlankLine).map(Point::parse).toSet()
    val instructions = lines.subList(indexOfBlankLine + 1, lines.size).map(::parseFoldInstruction)
    return Puzzle(dots, instructions)
}