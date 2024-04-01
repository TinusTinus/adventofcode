package nl.mvdr.adventofcode.adventofcode2021.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = solve(lines, ::computeFuelCost)

/**
 * Computes the fuel cost for a crab to move the given [distance].
 */
private fun computeFuelCost(distance: Int) = distance

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day07-2021.txt")
    logger.info { result }
}
