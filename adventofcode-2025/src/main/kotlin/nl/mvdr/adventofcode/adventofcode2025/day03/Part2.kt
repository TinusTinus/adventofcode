package nl.mvdr.adventofcode.adventofcode2025.day03

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Long =
    lines.map(::parseBank)
        .map { it.computeMaxJoltage(12) }
        .sum()

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day03.txt")
    logger.info { result }
}
