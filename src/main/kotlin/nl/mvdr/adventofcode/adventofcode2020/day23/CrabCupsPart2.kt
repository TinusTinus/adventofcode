package nl.mvdr.adventofcode.adventofcode2020.day23


import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Long {
    val state = CrabCupsGameState(lines.first(), 1_000_000)
    state.perform(10_000_000)
    return state.productOfCupsClockwiseFrom1()
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day23-2020.txt")
    logger.info { result }
}
