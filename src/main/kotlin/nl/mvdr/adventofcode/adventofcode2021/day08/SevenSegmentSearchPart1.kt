package nl.mvdr.adventofcode.adventofcode2021.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = lines.sumOf(::countEasyDigits)

/**
 * Counts how many times digits 1, 4, 7, or 8 appear in the given entry's output value.
 */
private fun countEasyDigits(entry: String): Int {
    val (_, outputValue) = entry.split(" | ")

    val digits = outputValue.split(" ")

    val ones = digits.count { it.length == 2 }
    val fours = digits.count { it.length == 4 }
    val sevens = digits.count { it.length == 3 }
    val eights = digits.count { it.length == 7 }
    return ones + fours + sevens + eights
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day08-2021.txt")
    logger.info { result }
}
