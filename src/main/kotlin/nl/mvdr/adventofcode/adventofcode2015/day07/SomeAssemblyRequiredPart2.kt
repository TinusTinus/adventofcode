package nl.mvdr.adventofcode.adventofcode2015.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val part1Result = solvePart1(lines)
    val instructions = parseInstructions(lines)
    instructions["b"] = SignalExpression(part1Result)
    return evaluate(instructions, "a")
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day07-2015.txt")
    logger.info { result }
}
