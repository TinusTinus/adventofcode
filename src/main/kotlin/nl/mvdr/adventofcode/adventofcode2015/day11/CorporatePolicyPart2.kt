package nl.mvdr.adventofcode.adventofcode2015.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: List<String>): String = findNextPassword(solvePart1(lines))

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day11-2015.txt")
    logger.info { result }
}
