package nl.mvdr.adventofcode.adventofcode2021.day16

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = decode(lines.first()).evaluate()

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day16-2021.txt")
    logger.info { result }
}
