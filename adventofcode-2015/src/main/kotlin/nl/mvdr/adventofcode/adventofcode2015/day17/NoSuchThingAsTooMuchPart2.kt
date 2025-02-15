package nl.mvdr.adventofcode.adventofcode2015.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>, eggnog: Int = 150): Int {
    val containers = lines.map(String::toInt).toList()
    return generateSequence(1) { it + 1 }
        .map { countWaysToFitInContainers(containers, eggnog, it) }
        .first { 0 < it }
}


fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day17-2015.txt")
    logger.info { result }
}
