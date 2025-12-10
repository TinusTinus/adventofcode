package nl.mvdr.adventofcode.adventofcode2025.day09

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = parseTileFloor(lines).findLargestRedOrGreenRectangleArea()

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day09.txt")
    logger.info { result }
}
