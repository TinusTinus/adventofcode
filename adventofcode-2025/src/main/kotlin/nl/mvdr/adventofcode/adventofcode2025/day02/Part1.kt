package nl.mvdr.adventofcode.adventofcode2025.day02

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Long =
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
    return length % 2 == 0 && idString.take(length / 2) == idString.substring(length / 2)
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day02.txt")
    logger.info { result }
}
