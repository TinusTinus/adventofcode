package nl.mvdr.adventofcode.adventofcode2021.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val targetArea = parseTargetArea(lines.first())
    return getProbes(targetArea).maxOf(Probe::maxY)
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day17-2021.txt")
    logger.info { result }
}
