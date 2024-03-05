package nl.mvdr.adventofcode.adventofcode2015.day18

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>, steps: Int = 100): Int = parseGrid(lines, true).step(steps).count()

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day18-2015.txt")
    logger.info { result }
}
