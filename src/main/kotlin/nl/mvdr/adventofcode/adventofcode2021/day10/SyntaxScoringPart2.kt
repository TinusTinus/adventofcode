package nl.mvdr.adventofcode.adventofcode2021.day10

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Long {
    val scores = lines.map(::score)
        .filter { it.syntaxErrorScore == 0 }
        .map(Score::completionScore)
        .sorted()
        .toList()
    logger.debug { "Completion scores: $scores" }
    return scores[scores.size / 2]
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day10-2021.txt")
    logger.info { result }
}
