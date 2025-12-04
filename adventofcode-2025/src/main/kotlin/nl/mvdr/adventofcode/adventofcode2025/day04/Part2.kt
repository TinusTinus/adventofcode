package nl.mvdr.adventofcode.adventofcode2025.day04

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = solve(parseInput(lines))

private fun solve(rolls: Set<Point>): Int {
    val newRolls = rolls.filter { roll -> 4 <= roll.neighboursIncludingDiagonals().count(rolls::contains) }.toSet()
    return when(val removed = rolls.size - newRolls.size) {
        0 -> 0
        else -> removed + solve(newRolls)
    }
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day04.txt")
    logger.info { result }
}
