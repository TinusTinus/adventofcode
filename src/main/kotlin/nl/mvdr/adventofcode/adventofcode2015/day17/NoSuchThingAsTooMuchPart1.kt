package nl.mvdr.adventofcode.adventofcode2015.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>, eggnog: Int = 150): Int = countWaysToFitInContainers(lines.map(String::toInt), eggnog)

/**
 * Counts the number of ways in which the given [eggnog] can fit exactly in the containers with the given [containerVolumes].
 * All volumes are in liters.
 */
private fun countWaysToFitInContainers(containerVolumes: List<Int>, eggnog: Int): Int = when {
    eggnog < 0 -> 0 // does not fit
    eggnog == 0 -> 1 // fits exactly in the containers already used
    containerVolumes.isEmpty() -> 0 // no more remaining containers
    else -> // either use the next container, or don't
        countWaysToFitInContainers(containerVolumes.subList(1, containerVolumes.size), eggnog - containerVolumes.first()) +
                countWaysToFitInContainers(containerVolumes.subList(1, containerVolumes.size), eggnog)
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day17-2015.txt")
    logger.info { result }
}
