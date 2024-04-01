package nl.mvdr.adventofcode.adventofcode2021.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = lines.sumOf(::countEasyDigits)

/**
 * Counts how many times digits 1, 4, 7, or 8 appear in the given entry's output value.
 */
private fun countEasyDigits(entry: String): Int = entry.split(" | ")
        .last()
        .split(" ")
        .map(::SignalPattern)
        .count(SignalPattern::isEasy)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day08-2021.txt")
    logger.info { result }
}
