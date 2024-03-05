package nl.mvdr.adventofcode.adventofcode2015.day12

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = sum(lines)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day12-2015.txt")
    logger.info { result }
}
