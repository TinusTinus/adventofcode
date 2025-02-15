package nl.mvdr.adventofcode.adventofcode2015.day14

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = raceForPoints(lines)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day14-2015.txt")
    logger.info { result }
}
