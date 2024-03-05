package nl.mvdr.adventofcode.adventofcode2015.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>, eggnogVolume: Int = 150): Int = countWaysToFitInContainers(parseContainers(lines), eggnogVolume)

/**
 * Returns a list of the containers, ordered small-to-large, as specified in the given [lines] from the puzzle input.
 */
private fun parseContainers(lines: List<String>): List<Int> = lines.map(String::toInt).sorted()

private fun countWaysToFitInContainers(containers: List<Int>, eggnogVolume: Int): Int = when {
    eggnogVolume < 0 -> 0 // does not fit
    eggnogVolume == 0 -> 1 // first exactly in the containers already used
    containers.isEmpty() -> 0 // no more remaining containers
    else -> // either use the next container, or don't
        countWaysToFitInContainers(containers.subList(1, containers.size), eggnogVolume - containers.first()) +
                countWaysToFitInContainers(containers.subList(1, containers.size), eggnogVolume)
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day17-2015.txt")
    logger.info { result }
}
