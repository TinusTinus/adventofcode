package nl.mvdr.adventofcode.adventofcode2020.day23


import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>, turns: Int = 100): String {
    val state = CrabCupsGameState(lines.toList())
    state.perform(turns)
    return state.cupsClockwiseFrom1()
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day23-2020.txt")
    logger.info { result }
}
