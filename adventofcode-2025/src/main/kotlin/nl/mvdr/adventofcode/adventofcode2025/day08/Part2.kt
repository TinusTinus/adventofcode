package nl.mvdr.adventofcode.adventofcode2025.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Long {
    val junctionBoxes = parseJunctionBoxes(lines)
    val pairs = findPairs(junctionBoxes)
    val lastPair = findCircuits(junctionBoxes, pairs).lastPair
    return lastPair.first().x.toLong() * lastPair.last().x.toLong()
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day08.txt")
    logger.info { result }
}
