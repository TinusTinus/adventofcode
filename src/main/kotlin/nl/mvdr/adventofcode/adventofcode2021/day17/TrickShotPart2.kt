package nl.mvdr.adventofcode.adventofcode2021.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val targetArea = parseTargetArea(lines.first())
    return getProbes(targetArea).count { it.willReachTargetArea(targetArea) }
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day17-2021.txt")
    logger.info { result }
}
