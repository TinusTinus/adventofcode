package nl.mvdr.adventofcode.adventofcode2015.day22

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = GameState(parseBoss(lines)).manaToWin()!!

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day22-2015.txt")
    logger.info { result }
}
