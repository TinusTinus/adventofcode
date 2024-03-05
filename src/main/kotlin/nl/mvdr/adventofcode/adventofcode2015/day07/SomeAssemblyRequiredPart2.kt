package nl.mvdr.adventofcode.adventofcode2015.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val instructions = parseInstructions(lines)
    val instructionsCopy = instructions.toMutableMap()

    val part1Result = evaluate(instructions, "a")

    instructionsCopy["b"] = SignalExpression(part1Result)
    return evaluate(instructionsCopy, "a")
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day07-2015.txt")
    logger.info { result }
}
