package nl.mvdr.adventofcode.adventofcode2025.day02

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Long =
    lines.first()
        .split(",")
        .map { it -> it.split("-") }
        .map { it -> it.first().toLong() .. it.last().toLong() }
        .flatMap { it -> it.asSequence() }
        .filter(::isInvalid)
        .sum()

private fun isInvalid(id: Long): Boolean {
    val idString = id.toString()
    val length = idString.length
    return (1 ..< length)
        .filter { it -> length % it == 0 }
        .any { it -> isRepeating(idString, it) }
}

private fun isRepeating(idString: String, substringLength: Int) =
    (0..<idString.length / substringLength)
        .map { it -> idString.substring(it * substringLength, (it + 1) * substringLength) }
        .distinct()
        .count() == 1

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day02.txt")
    logger.info { result }
}
