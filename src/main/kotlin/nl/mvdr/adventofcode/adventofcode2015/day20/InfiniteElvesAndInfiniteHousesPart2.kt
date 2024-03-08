package nl.mvdr.adventofcode.adventofcode2015.day20

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import kotlin.math.sqrt

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int = lowestHouseNumber(lines.first().toInt(), 11)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day20-2015.txt")
    logger.info { result }
}
