package nl.mvdr.adventofcode.adventofcode2015.day10

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int = lookAndSay(lines.first(), 40).length

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day10-2015.txt")
    logger.info { result }
}
