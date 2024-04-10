package nl.mvdr.adventofcode.adventofcode2021.day15

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.point.plus

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(expand(parseRisks(lines)))

private fun expand(risks: Map<Point, Int>): Map<Point, Int> {
    val result = mutableMapOf<Point, Int>()

    for (xOffset in 0 until 5) {
        for (yOffset in 0 until 5) {
            val pointOffset = Point(xOffset, yOffset)
            val valueOffset = xOffset + yOffset
            risks.entries.forEach { result[it.key + pointOffset] = (it.value + valueOffset) % 10 } // TODO this risk offset calculation is incorrect
        }
    }

    return result
}


fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day15-2021.txt")
    logger.info { result }
}
