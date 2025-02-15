package nl.mvdr.adventofcode.adventofcode2021.day18

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val numbers = lines.map(::parseSnailfishNumber).toSet()
    return numbers.flatMap { firstNumber -> numbers.filter { it != firstNumber }.map { Pair(firstNumber, it) } }
        .map { it.first + it.second }
        .maxOf(SnailfishNumber::magnitude)
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day18-2021.txt")
    logger.info { result }
}
