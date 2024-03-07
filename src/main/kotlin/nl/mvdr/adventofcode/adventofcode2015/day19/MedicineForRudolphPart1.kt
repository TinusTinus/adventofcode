package nl.mvdr.adventofcode.adventofcode2015.day19

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(linesSequence: Sequence<String>): Int {
    val lines = linesSequence.toList()
    val molecule = lines.last()
    return lines.dropLast(2)
        .map(::parseReplacement)
        .flatMap { it.apply(molecule) }
        .distinct()
        .count()
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day19-2015.txt")
    logger.info { result }
}
