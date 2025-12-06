package nl.mvdr.adventofcode.adventofcode2025.day06

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(linesSequence: Sequence<String>): Long {
    return 3L // TODO implement
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day06.txt")
    logger.info { result }
}
