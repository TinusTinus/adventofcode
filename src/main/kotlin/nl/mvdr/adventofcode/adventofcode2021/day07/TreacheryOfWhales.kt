package nl.mvdr.adventofcode.adventofcode2021.day07

import kotlin.math.abs

/**
 * Solves the puzzle, given the [lines] from the puzzle input.
 * The [fuelFunction] specifies how to compute the fuel cost when moving a given distance.
 */
fun solve(lines: Sequence<String>, fuelFunction: (Int) -> Int): Int {
    val initialPositions = lines.first().split(",").map(String::toInt)
    return (initialPositions.min() .. initialPositions.max()).minOf { computeFuelCost(initialPositions, it, fuelFunction) }
}

/**
 * Computes the total fuel cost for the crabs to move from their given [initialPositions] to the given [targetPosition].
 */
private fun computeFuelCost(initialPositions: List<Int>, targetPosition: Int, fuelFunction: (Int) -> Int) =
    initialPositions.sumOf { fuelFunction.invoke(abs(it - targetPosition)) }
