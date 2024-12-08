package nl.mvdr.adventofcode.adventofcode2021.day18

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = lines.map(::parseSnailfishNumber)
    .toList()
    .reduce(SnailfishNumber::plus)
    .magnitude()

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day18-2021.txt")
    logger.info { result }
}
