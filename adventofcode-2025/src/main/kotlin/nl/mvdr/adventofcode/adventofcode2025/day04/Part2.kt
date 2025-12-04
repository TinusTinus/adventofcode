package nl.mvdr.adventofcode.adventofcode2025.day04

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    var rolls = parseInput(lines)
    val startingRolls = rolls.size

    var done = false
    while (!done) {
        val newRolls = rolls.filter { roll -> 4 <= roll.neighboursIncludingDiagonals().count(rolls::contains) }.toSet()
        done = newRolls == rolls
        rolls = newRolls
    }

    return startingRolls - rolls.size
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day04.txt")
    logger.info { result }
}
