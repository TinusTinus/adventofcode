package nl.mvdr.adventofcode.adventofcode2015.day05

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: List<String>): Int {
    return lines.count { line -> isNice(line) }
}

private fun isNice(string: String): Boolean = containsTwoPairs(string) && containsRepeatingLetter(string)

private fun containsTwoPairs(string: String): Boolean {
    return string.toCharArray()
        .withIndex()
        .any {
            (firstIndex, _) -> firstIndex <= string.length - 2 && string.substring(firstIndex + 2).toCharArray().withIndex().any {
                (secondIndex, _) -> string[firstIndex] == string[secondIndex] && string[firstIndex + 1] == string[secondIndex + 1]
            }
        }
}

private fun containsRepeatingLetter(string: String): Boolean {
    val charArray = string.toCharArray()
    return charArray.withIndex().any { (index, char) -> index < string.length - 2 && char == charArray[index + 2] }
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day05-2015.txt")
    logger.info { result }
}
