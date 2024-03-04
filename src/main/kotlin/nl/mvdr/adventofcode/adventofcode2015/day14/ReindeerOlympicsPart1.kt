package nl.mvdr.adventofcode.adventofcode2015.day14

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>) = lines.map(::parseReindeer).maxOf{ it.distanceAfter(2503) }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day14-2015.txt")
    logger.info { result }
}
