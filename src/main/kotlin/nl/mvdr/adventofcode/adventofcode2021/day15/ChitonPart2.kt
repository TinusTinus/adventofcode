package nl.mvdr.adventofcode.adventofcode2021.day15

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.point.plus

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(expand(parseRisks(lines)))

private fun expand(risks: Map<Point, Int>): Map<Point, Int> {
    val result = mutableMapOf<Point, Int>()

    val width = Point.maxX(risks.keys) + 1
    val height = Point.maxY(risks.keys) + 1

    for (xOffset in 0 until 5) {
        for (yOffset in 0 until 5) {
            val pointOffset = Point(xOffset * width, yOffset * height)
            val valueOffset = xOffset + yOffset
            risks.entries.forEach { result[it.key + pointOffset] = calculateRiskValue(it.value, valueOffset) }
        }
    }

    return result
}

private fun calculateRiskValue(risk: Int, offset: Int): Int {
    var result = risk + offset
    while (9 < result) {
        result -= 9
    }
    return result
}


fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day15-2021.txt")
    logger.info { result }
}
