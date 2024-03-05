package nl.mvdr.adventofcode.adventofcode2015.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>) = 3 // TODO

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day16-2015.txt")
    logger.info { result }
}
