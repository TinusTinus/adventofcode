package nl.mvdr.adventofcode.adventofcode2025.day05

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(linesSequence: Sequence<String>) = parseInput(linesSequence.toList()).countFreshIngredients()

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day05.txt")
    logger.info { result }
}
