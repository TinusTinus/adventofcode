package nl.mvdr.adventofcode.adventofcode2015.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = selectFirstGroup(lines, 3)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day24-2015.txt")
    logger.info { result }
}
