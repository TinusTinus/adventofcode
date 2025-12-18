package nl.mvdr.adventofcode.adventofcode2025.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = createAlgorithm(lines)
    .getAllPaths("you", "out")
    .size

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day11.txt")
    logger.info { result }
}
