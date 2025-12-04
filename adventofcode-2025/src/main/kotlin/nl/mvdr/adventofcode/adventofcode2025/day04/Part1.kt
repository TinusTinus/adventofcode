package nl.mvdr.adventofcode.adventofcode2025.day04

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = solve(parseInput(lines))

private fun solve(rolls: Set<Point>) =
    rolls.count { roll -> roll.neighboursIncludingDiagonals().count(rolls::contains) < 4 }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day04.txt")
    logger.info { result }
}
