package nl.mvdr.adventofcode.adventofcode2020.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = Lobby(lines).simulateDays().blackTileCount

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day24-2020.txt")
    logger.info { result }
}
