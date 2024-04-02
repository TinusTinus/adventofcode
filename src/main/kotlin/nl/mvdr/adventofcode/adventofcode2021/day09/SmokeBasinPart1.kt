package nl.mvdr.adventofcode.adventofcode2021.day09

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val heightMap = Point.parse2DMap(lines.toList(), ::parseHeight)

    return heightMap.entries
        .filter { isLowPoint(it.key, heightMap) }
        .sumOf { it.value + 1 }
}

private fun parseHeight(c: Char) = c.toString().toInt()

/**
 * Checks whether the given [point] is a low point in the given [heightMap].
 */
private fun isLowPoint(point: Point, heightMap: Map<Point, Int>) = point.neighbours()
    .map { heightMap[it] }
    .all { it == null || heightMap[point]!! < it }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day09-2021.txt")
    logger.info { result }
}
