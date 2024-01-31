package nl.mvdr.adventofcode.adventofcode2015.day01

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.ListSolver

private val logger = KotlinLogging.logger{}

/**
 * Solution to the day 1 puzzle of 2015's Advent of Code:
 * <a href="https://adventofcode.com/2015/day/1">Not Quite Lisp</a>.
 *
 * @author Martijn van de Rijdt
 */
class NotQuiteLispPart2: ListSolver<Int> {
    override fun solve(lines: List<String>): Int = findCharacter(lines.first())

    /**
     * Finds the position of the first character which will send Santa to the basement.
     *
     * @param text instructions, where '(' means: go up a floor and ')' means: go down a floor
     * @return position of the instruction
     */
    private fun findCharacter(text: String): Int {
        val characters = text.toCharArray()
        var floor = 0
        var position = 0

        while (0 <= floor) {
            when(characters[position]) {
                '(' -> floor++
                ')' -> floor--
                else -> throw IllegalArgumentException("Unexpected character found in puzzle input")
            }
            position++
        }
        return position
    }
}

/**
 * Main method. Solves the puzzle for the given input.
 */
fun main() {
    val result = NotQuiteLispPart2().solve("input-day01-2015.txt")
    logger.info { result }
}
