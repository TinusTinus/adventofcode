package nl.mvdr.adventofcode.adventofcode2015.day05

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: List<String>): Int {
    return lines.count { line -> isNice(line) }
}

private fun isNice(string: String): Boolean = containsPairTwice(string) && containsRepeatingLetter(string)

private fun containsPairTwice(string: String): Boolean =
    string.withIndex()
        .any { (firstIndex, _) ->
            firstIndex <= string.length - 2
                    && string.substring(firstIndex + 2).contains(string.substring(firstIndex until firstIndex + 2))
        }

private fun containsRepeatingLetter(string: String) =
    string.withIndex().any { (index, char) -> index < string.length - 2 && char == string[index + 2] }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day05-2015.txt")
    logger.info { result }
}
