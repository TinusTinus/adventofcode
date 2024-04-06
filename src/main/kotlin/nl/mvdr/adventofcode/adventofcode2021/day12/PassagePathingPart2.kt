package nl.mvdr.adventofcode.adventofcode2021.day12

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = CaveSystem(lines).countPathsToEnd(::canVisit)

/**
 * Determines whether it is allowed to visit the given [cave], given that
 * [visited] contains all caves which have already previously been visited on this path.
 */
private fun canVisit(cave: Cave, visited: List<Cave>) = cave.big ||
        !visited.contains(cave) ||
        (cave.name != "start" && cave.name != "end" && visited.all { visitedCave -> visitedCave.big || visited.count { it == visitedCave } == 1 })

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day12-2021.txt")
    logger.info { result }
}
