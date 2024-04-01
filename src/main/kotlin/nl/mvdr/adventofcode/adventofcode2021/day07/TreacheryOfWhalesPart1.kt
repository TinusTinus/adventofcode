package nl.mvdr.adventofcode.adventofcode2021.day07

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import kotlin.math.abs
import kotlin.math.roundToInt

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val initialPositions = lines.first().split(",").map(String::toInt)
    val targetPosition = (initialPositions.sum().toDouble() / initialPositions.size).roundToInt()
    logger.info { "Target position: $targetPosition" } // TODO reduce log level
    return initialPositions.sumOf { abs(targetPosition - it) }
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day07-2021.txt")
    logger.info { result }
}
