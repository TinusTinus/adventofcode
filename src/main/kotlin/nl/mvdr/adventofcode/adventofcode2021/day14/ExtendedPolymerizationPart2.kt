package nl.mvdr.adventofcode.adventofcode2021.day14

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = Manual(lines.toList()).solve(40)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day14-2021.txt")
    logger.info { result }
}
