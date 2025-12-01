package nl.mvdr.adventofcode.adventofcode2025.day01

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Direction
import nl.mvdr.adventofcode.solver.FunctionSolver
import kotlin.math.absoluteValue
import kotlin.math.min

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    return 3 // TODO implement!
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day01.txt") // 6603 is too high
    logger.info { result }
}
