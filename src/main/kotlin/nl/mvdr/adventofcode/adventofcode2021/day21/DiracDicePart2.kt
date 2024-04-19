package nl.mvdr.adventofcode.adventofcode2021.day21

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = DiracGame(parsePlayers(lines)).play().values.max()

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day21-2021.txt")
    logger.info { result }
}
