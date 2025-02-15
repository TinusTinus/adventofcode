package nl.mvdr.adventofcode.adventofcode2015.day05

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val VOWELS = setOf('a', 'e', 'i', 'o', 'u')
private val FORBIDDEN = setOf("ab", "cd", "pq", "xy")

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = lines.count { isNice(it) }

private fun isNice(string: String): Boolean =
    3 <= countVowels(string)
            && containsRepeatingLetter(string)
            && !containsForbiddenSubstring(string)

private fun countVowels(string: String) = string.toCharArray().count(VOWELS::contains)

private fun containsRepeatingLetter(string: String) =
    string.withIndex().any { (index, char) -> index < string.length - 1 && char == string[index + 1] }


private fun containsForbiddenSubstring(string: String): Boolean = FORBIDDEN.any(string::contains)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day05-2015.txt")
    logger.info { result }
}
