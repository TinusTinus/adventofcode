package nl.mvdr.adventofcode.adventofcode2021.day10

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = lines.map(::score).sumOf(Score::syntaxErrorScore)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day09-2021.txt")
    logger.info { result }
}
