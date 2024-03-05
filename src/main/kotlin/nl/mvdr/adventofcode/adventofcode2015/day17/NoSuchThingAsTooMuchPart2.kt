package nl.mvdr.adventofcode.adventofcode2015.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: List<String>, eggnog: Int = 150): Int = countWaysToFitInContainers(lines.map(String::toInt), eggnog) // TODO


fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day17-2015.txt")
    logger.info { result }
}
