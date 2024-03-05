package nl.mvdr.adventofcode.adventofcode2015.day18

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>, steps: Int = 100): Int = 3 // TODO

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day18-2015.txt")
    logger.info { result }
}
