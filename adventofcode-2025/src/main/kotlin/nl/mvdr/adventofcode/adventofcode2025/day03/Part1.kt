package nl.mvdr.adventofcode.adventofcode2025.day03

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int =
    3 // TODO

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day03.txt")
    logger.info { result }
}
