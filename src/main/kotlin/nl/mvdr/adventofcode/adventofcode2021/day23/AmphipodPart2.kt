package nl.mvdr.adventofcode.adventofcode2021.day23

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = State(addExtraLines(lines)).energyCost

private fun addExtraLines(linesSequence: Sequence<String>): MutableList<String> {
    val lines = linesSequence.toMutableList()
    lines.add(3, "  #D#C#B#A#")
    lines.add(4, "  #D#B#A#C#")
    return lines
}


fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day23-2021.txt")
    logger.info { result }
}
