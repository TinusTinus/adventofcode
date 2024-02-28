package nl.mvdr.adventofcode.adventofcode2015.day12

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: List<String>) = sum(lines, filterRed = true)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day12-2015.txt")
    logger.info { result }
}
