package nl.mvdr.adventofcode.adventofcode2021.day23

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = 3

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day23-2021.txt")
    logger.info { result }
}
