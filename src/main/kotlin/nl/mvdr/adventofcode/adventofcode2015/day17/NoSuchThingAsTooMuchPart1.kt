package nl.mvdr.adventofcode.adventofcode2015.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>, eggnog: Int = 150): Int = countWaysToFitInContainers(lines.map(String::toInt), eggnog)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day17-2015.txt")
    logger.info { result }
}
