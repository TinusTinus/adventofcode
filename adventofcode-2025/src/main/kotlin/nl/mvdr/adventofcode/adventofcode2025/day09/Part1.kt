package nl.mvdr.adventofcode.adventofcode2025.day09

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.solver.FunctionSolver
import kotlin.math.abs

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>, numberOfPairs: Int = 1000): Int {
    val points = lines.map(Point::parse).toSet()

    return points.flatMap { firstCorner -> points.map { secondCorner -> (abs(firstCorner.x - secondCorner.x) + 1) * (abs(firstCorner.y - secondCorner.y) + 1) } }
        .max()
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day09.txt")
    logger.info { result }
}
