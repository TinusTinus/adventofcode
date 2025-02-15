package nl.mvdr.adventofcode.adventofcode2020.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = Lobby(lines).blackTileCount

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day24-2020.txt")
    logger.info { result }
}
