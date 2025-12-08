package nl.mvdr.adventofcode.adventofcode2025.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>, numberOfPairs: Int = 1000): Int {
    val junctionBoxes = parseJunctionBoxes(lines)
    val pairs = findPairs(junctionBoxes).take(numberOfPairs)
    val circuits = findCircuits(junctionBoxes, pairs).circuits
    return circuits.map { it.size }
        .sorted()
        .takeLast(3)
        .reduce(Int::times)
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day08.txt")
    logger.info { result }
}
