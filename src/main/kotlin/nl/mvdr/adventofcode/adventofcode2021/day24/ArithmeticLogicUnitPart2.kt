package nl.mvdr.adventofcode.adventofcode2021.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = Monad(lines).findModelNumber(true)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day24-2021.txt")
    logger.info { result }
}
