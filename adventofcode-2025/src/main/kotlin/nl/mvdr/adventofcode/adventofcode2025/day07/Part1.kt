package nl.mvdr.adventofcode.adventofcode2025.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = parseManifold(lines.toList()).countSplits()

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day07.txt")
    logger.info { result }
}
