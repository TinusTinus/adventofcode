package nl.mvdr.adventofcode.adventofcode2015

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.ListSolver

private val logger = KotlinLogging.logger{}

/**
 * Solution to the day 1 puzzle of 2015's Advent of Code:
 * <a href="https://adventofcode.com/2015/day/1">Not Quite Lisp</a>.
 *
 * @author Martijn van de Rijdt
 */
class NotQuiteLispPart1: ListSolver<Int> {
    /**
     * Solver method.
     *
     * @param lines list of strings, each of which corresponds to a line from the input
     * @return solution to the puzzle for the given input
     */
    override fun solve(lines: List<String>): Int = countInstructions(lines.first())

    /**
     * Counts the instructions in the given text, determining the end floor.
     *
     * @param text instructions, where '(' means: go up a floor and ')' means: go down a floor
     * @return end floor
     */
    private fun countInstructions(text: String): Int {
        return text.toCharArray()
            .map {
                when (it) {
                    '(' -> 1
                    ')' -> -1
                    else -> throw IllegalArgumentException("Unexpected character found in input: $it")
                }
            }
            .sum()
    }
}

/**
 * Main method. Solves the puzzle for the given input.
 */
fun main() {
    val result = NotQuiteLispPart1().solve("input-day01-2015.txt")
    logger.info { result }
}
