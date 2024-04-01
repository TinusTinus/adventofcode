package nl.mvdr.adventofcode.adventofcode2021.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import kotlin.math.abs

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(lines, ::computeFuelCost)

/**
 * Computes the fuel cost for a crab to move from its given [initialPosition] to the given [targetPosition].
 */
private fun computeFuelCost(initialPosition: Int, targetPosition: Int) = triangularNumber(abs(initialPosition - targetPosition))

/**
 * Computes the sum of all natural numbers which are at most [n].
 * Put differently, the value of 1 + 2 + 3 + ... + n .
 * This is also sometimes referred to as the Gauss summation.
 */
// See also https://en.wikipedia.org/wiki/Triangular_number#Formula
// or (Dutch) https://nl.wikipedia.org/wiki/Somformule_van_Gauss.
private fun triangularNumber(n: Int) = (n * (n + 1)) / 2

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day07-2021.txt")
    logger.info { result }
}
