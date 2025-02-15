package nl.mvdr.adventofcode.adventofcode2015.day14

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = raceForDistance(lines)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day14-2015.txt")
    logger.info { result }
}
