package nl.mvdr.adventofcode.adventofcode2021.day06

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = School(lines).simulateDays().countFish()

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day06-2021.txt")
    logger.info { result }
}
