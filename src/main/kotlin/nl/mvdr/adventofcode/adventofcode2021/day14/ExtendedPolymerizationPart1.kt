package nl.mvdr.adventofcode.adventofcode2021.day14

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = parseManual(lines.toList()).solve(10)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day14-2021.txt")
    logger.info { result }
}
