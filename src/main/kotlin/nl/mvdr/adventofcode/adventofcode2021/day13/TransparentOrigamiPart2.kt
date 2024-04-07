package nl.mvdr.adventofcode.adventofcode2021.day13

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = parsePuzzle(lines.toList()).solve()

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day13-2021.txt")
    logger.info { result }
}
