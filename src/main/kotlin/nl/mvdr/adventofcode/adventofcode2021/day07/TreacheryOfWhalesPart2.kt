package nl.mvdr.adventofcode.adventofcode2021.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(lines, ::computeFuelCost)

/**
 * Computes the fuel cost for a crab to move the given [distance].
 */
// See also https://en.wikipedia.org/wiki/Triangular_number#Formula
// or (Dutch) https://nl.wikipedia.org/wiki/Somformule_van_Gauss.
private fun computeFuelCost(distance: Int) = distance * (distance + 1) / 2

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day07-2021.txt")
    logger.info { result }
}
