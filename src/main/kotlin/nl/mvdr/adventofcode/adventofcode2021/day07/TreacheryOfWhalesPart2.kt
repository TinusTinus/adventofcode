package nl.mvdr.adventofcode.adventofcode2021.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import kotlin.math.abs

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(lines, ::computeFuelCost)

/**
 * Computes the fuel cost for a crab to move from its given [initialPosition] to the given [targetPosition].
 */
private fun computeFuelCost(initialPosition: Int, targetPosition: Int) = gauss(abs(initialPosition - targetPosition))

private fun gauss(n: Int) = (n * (n + 1)) / 2

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day07-2021.txt")
    logger.info { result }
}
