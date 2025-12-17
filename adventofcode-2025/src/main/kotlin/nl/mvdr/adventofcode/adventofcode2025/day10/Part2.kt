package nl.mvdr.adventofcode.adventofcode2025.day10

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = lines.map(::parseMachine)
    .sumOf(Machine::computeFewestButtonPressesToRequiredJoltage)

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day10.txt")
    logger.info { result }
}
