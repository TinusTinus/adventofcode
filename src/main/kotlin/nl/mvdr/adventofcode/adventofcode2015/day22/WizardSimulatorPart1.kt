package nl.mvdr.adventofcode.adventofcode2015.day22

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val boss = parseBoss(lines)
    val gameState = GameState(boss)
    return 3 // TODO
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day21-2015.txt")
    logger.info { result }
}
