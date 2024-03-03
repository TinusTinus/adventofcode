package nl.mvdr.adventofcode.adventofcode2015.day13

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>) = maxTotalHappiness(lines)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day13-2015.txt")
    logger.info { result }
}
