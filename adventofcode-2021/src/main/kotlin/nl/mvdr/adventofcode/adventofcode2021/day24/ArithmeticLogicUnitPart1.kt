package nl.mvdr.adventofcode.adventofcode2021.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = Monad(lines).findModelNumber()

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day24-2021.txt")
    logger.info { result }
}
