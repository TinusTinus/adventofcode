package nl.mvdr.adventofcode.adventofcode2015.day05

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.ListSolver

private val logger = KotlinLogging.logger{}

/**
 * Solution to the day 1 puzzle of 2015's Advent of Code:
 * <a href="https://adventofcode.com/2015/day/5">Doesn't He Have Intern-Elves For This?</a>.
 *
 * @author Martijn van de Rijdt
 */
class InternElvesPart1: ListSolver<Int> {
    override fun solve(lines: List<String>): Int {
        return lines.count { line -> isNice(line) }
    }
}

val VOWELS = setOf('a', 'e', 'i', 'o', 'u')
val FORBIDDEN = setOf("ab", "cd", "pq", "xy")

private fun isNice(string: String): Boolean {
    return 3 <= countVowels(string) && !containsForbiddenSubstring(string) // TODO implement the second rule as well
}

private fun countVowels(string: String) = string.toCharArray().count(VOWELS::contains)

private fun containsForbiddenSubstring(string: String): Boolean = FORBIDDEN.any(string::contains)

/**
 * Main method. Solves the puzzle for the given input.
 */
fun main() {
    val result = InternElvesPart1().solve("input-day05-2015.txt")
    logger.info { result }
}
