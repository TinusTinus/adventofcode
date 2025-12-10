package nl.mvdr.adventofcode.adventofcode2025.day10

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.adventofcode2025.day08.findCircuits
import nl.mvdr.adventofcode.adventofcode2025.day08.findPairs
import nl.mvdr.adventofcode.adventofcode2025.day08.parseJunctionBoxes
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = lines.map(::parseMachine).sumOf(Machine::computeFewestButtonPressesToInit)

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day10.txt")
    logger.info { result }
}
