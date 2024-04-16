package nl.mvdr.adventofcode.adventofcode2021.day20

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(lines, 50)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day20-2021.txt")
    logger.info { result }
}
