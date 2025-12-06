package nl.mvdr.adventofcode.adventofcode2025.day06

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = parse(lines.toList()).sumOf(Problem::solve)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day06.txt")
    logger.info { result }
}
