package nl.mvdr.adventofcode.adventofcode2021.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import kotlin.math.abs

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val initialPositions = lines.first().split(",").map(String::toInt)
    return (initialPositions.min() .. initialPositions.max()).minOf { computeFuelCost(initialPositions, it) }
}

/**
 * Computes the fuel cost for the crabs to move from their given [initialPositions] to the given [targetPosition].
 */
private fun computeFuelCost(initialPositions: List<Int>, targetPosition: Int) =
    initialPositions.sumOf { abs(targetPosition - it) }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day07-2021.txt")
    logger.info { result }
}
