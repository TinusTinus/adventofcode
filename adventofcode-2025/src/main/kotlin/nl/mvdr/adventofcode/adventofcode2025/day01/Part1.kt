package nl.mvdr.adventofcode.adventofcode2025.day01

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int =
    3 // TODO implement!

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day01.txt")
    logger.info { result }
}
