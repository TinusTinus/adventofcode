package nl.mvdr.adventofcode.adventofcode2015.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = selectFirstGroup(lines, 4)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day24-2015.txt")
    logger.info { result }
}
