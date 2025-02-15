package nl.mvdr.adventofcode.adventofcode2021.day21

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = DeterministicGame(parsePlayers(lines)).play()

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day21-2021.txt")
    logger.info { result }
}
