package nl.mvdr.adventofcode.adventofcode2025.day06

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = parsePart2(lines.toList()).sumOf(Problem::solve)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day06.txt")
    logger.info { result }
}
