package nl.mvdr.adventofcode.adventofcode2015.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val instructions = parseInstructions(lines)
    return evaluate(instructions, "a")
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day07-2015.txt")
    logger.info { result }
}
