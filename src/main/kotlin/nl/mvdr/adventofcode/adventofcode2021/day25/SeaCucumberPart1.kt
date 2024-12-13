package nl.mvdr.adventofcode.adventofcode2021.day25

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = parseSeafloor(lines.toList()).stepsToStopMoving

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day25-2021.txt")
    logger.info { result }
}
