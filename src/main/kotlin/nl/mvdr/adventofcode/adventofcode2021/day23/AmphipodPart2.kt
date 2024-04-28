package nl.mvdr.adventofcode.adventofcode2021.day23

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(linesSequence: Sequence<String>): Int {
    val lines = linesSequence.toMutableList()
    lines.add(3, "  #D#C#B#A#")
    lines.add(4, "  #D#B#A#C#")
    val state = State(lines, Burrow(4))
    return state.computeEnergyCost()
}



fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day23-2021.txt")
    logger.info { result }
}
