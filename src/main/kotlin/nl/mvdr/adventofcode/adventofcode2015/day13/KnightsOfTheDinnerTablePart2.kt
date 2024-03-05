package nl.mvdr.adventofcode.adventofcode2015.day13

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = maxTotalHappiness(lines, "Me")

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day13-2015.txt")
    logger.info { result }
}
