package nl.mvdr.adventofcode.adventofcode2021.day22

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(lines, false)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day22-2021.txt")
    logger.info { result }
}
