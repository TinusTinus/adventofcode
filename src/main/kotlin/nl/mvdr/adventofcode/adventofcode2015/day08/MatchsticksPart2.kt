package nl.mvdr.adventofcode.adventofcode2015.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int = lines.sumOf{ encodedStringLength(it) - it.length }

fun encodedStringLength(string: String): Int = string.length + 2 + string.count { it == '"' || it == '\\' }

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day08-2015.txt")
    logger.info { result }
}
