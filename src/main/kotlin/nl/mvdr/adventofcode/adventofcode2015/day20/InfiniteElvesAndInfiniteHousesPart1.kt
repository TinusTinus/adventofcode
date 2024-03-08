package nl.mvdr.adventofcode.adventofcode2015.day20

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = lowestHouseNumber(lines.first().toInt(), 10)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day20-2015.txt")
    logger.info { result }
}
