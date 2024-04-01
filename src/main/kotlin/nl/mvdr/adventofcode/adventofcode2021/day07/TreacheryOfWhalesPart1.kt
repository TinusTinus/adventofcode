package nl.mvdr.adventofcode.adventofcode2021.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import kotlin.math.abs

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val initialPositions = lines.first().split(",").map(String::toInt)
    return (initialPositions.min() .. initialPositions.max()).minOf { position -> initialPositions.sumOf { abs(position - it) } }
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day07-2021.txt")
    logger.info { result }
}
