package nl.mvdr.adventofcode.adventofcode2025.day09

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = parseTileFloor(lines).findLargestRectangleArea()

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day09.txt")
    logger.info { result }
}
