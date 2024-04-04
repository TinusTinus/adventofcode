package nl.mvdr.adventofcode.adventofcode2021.day10

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val scores = lines.map(::completionScore)
        .sorted()
        .toList()
    return scores[scores.size / 2]
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day09-2021.txt")
    logger.info { result }
}
