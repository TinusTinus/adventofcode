package nl.mvdr.adventofcode.adventofcode2015.day09

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: List<String>): Int = getRouteWeights(lines).maxOrNull()!!

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day09-2015.txt")
    logger.info { result }
}
