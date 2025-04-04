package nl.mvdr.adventofcode.adventofcode2021.day23

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = State(lines.toList()).energyCost

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day23-2021.txt")
    logger.info { result }
}
