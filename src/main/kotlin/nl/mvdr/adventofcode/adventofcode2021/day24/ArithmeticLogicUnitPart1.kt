package nl.mvdr.adventofcode.adventofcode2021.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Long {
    val monad = parseProgram(lines)

    return 3 // TODO
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day24-2021.txt")
    logger.info { result }
}
