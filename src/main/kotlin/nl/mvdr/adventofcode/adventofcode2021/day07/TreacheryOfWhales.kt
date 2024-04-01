package nl.mvdr.adventofcode.adventofcode2021.day07

import kotlin.math.abs

/**
 * Solves the puzzle, given the [lines] from the puzzle input.
 * The [fuelFunction] specifies how to compute the fuel cost when moving a given distance.
 */
fun solve(lines: Sequence<String>, fuelFunction: (Int) -> Int) = solve(parse(lines), fuelFunction)

/**
 * Parses the puzzle input as a list containing the crabs' initial positions.
 * The first of the given [lines] should contain a comma-separated list of horizontal positions.
 * For example: "16,1,2,0,4,2,7,1,2,14"
 */
private fun parse(lines: Sequence<String>) = lines.first().split(",").map(String::toInt)

/**
 * Solves the puzzle, given the crabs' [initialPositions].
 */
private fun solve(initialPositions: List<Int>, fuelFunction: (Int) -> Int) =
    (initialPositions.min()..initialPositions.max()).minOf { computeFuelCost(initialPositions, it, fuelFunction) }

/**
 * Computes the total fuel cost for the crabs to move from their given [initialPositions] to the given [targetPosition].
 */
private fun computeFuelCost(initialPositions: List<Int>, targetPosition: Int, fuelFunction: (Int) -> Int) =
    initialPositions.sumOf { fuelFunction.invoke(abs(it - targetPosition)) }
