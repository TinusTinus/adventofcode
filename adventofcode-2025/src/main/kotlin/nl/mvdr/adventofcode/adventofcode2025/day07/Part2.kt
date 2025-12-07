package nl.mvdr.adventofcode.adventofcode2025.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = parseManifold(lines.toList()).countTimelines()

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day07.txt")
    logger.info { result }
}
