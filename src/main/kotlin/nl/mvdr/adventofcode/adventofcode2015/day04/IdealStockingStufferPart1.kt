package nl.mvdr.adventofcode.adventofcode2015.day04

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int = mine(lines, "00000")

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day04-2015.txt")
    logger.info { result }
}
