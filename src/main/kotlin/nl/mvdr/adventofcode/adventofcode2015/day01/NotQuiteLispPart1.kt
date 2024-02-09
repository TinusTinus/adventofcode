package nl.mvdr.adventofcode.adventofcode2015.day01

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

/**
 * Solution to the day 1 puzzle of 2015's Advent of Code:
 * <a href="https://adventofcode.com/2015/day/1">Not Quite Lisp</a>.
 */
fun solve(lines: List<String>): Int {
    return lines.first()
        .toCharArray()
        .map {
            when (it) {
                '(' -> 1
                ')' -> -1
                else -> throw IllegalArgumentException("Unexpected character found in input: $it")
            }
        }
        .sum()
}

/**
 * Main method. Solves the puzzle for the given input.
 */
fun main() {
    val solver = FunctionSolver(::solve)
    val result = solver.solve("input-day01-2015.txt")
    logger.info { result }
}
