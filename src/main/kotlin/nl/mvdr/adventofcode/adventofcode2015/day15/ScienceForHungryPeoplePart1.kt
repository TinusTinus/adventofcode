package nl.mvdr.adventofcode.adventofcode2015.day15

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = getPossibleCookies(lines).maxOf(Cookie::score)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day15-2015.txt")
    logger.info { result }
}
