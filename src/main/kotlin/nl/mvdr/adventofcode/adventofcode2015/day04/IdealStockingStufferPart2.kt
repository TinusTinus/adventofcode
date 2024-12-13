package nl.mvdr.adventofcode.adventofcode2015.day04

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int = mine(lines, "000000")

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day04-2015.txt")
    logger.info { result }
}
