package nl.mvdr.adventofcode.adventofcode2021.day12

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = CaveSystem(lines).countPathsToEnd(::canVisit)

private fun canVisit(cave: Cave, visited: List<Cave>) = cave.big || !visited.contains(cave)


fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day12-2021.txt")
    logger.info { result }
}
