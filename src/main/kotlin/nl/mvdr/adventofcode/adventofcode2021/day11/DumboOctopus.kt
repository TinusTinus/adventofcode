package nl.mvdr.adventofcode.adventofcode2021.day11

import nl.mvdr.adventofcode.point.Point

/**
 * Parses the given [lines] from the puzzle input as a map of octopus energy levels.
 */
fun parse(lines: Sequence<String>): Map<Point, Int> = Point.parse2DMap(lines.toList()) { character -> character.toString().toInt() }

/**
 * Counts the number of flashes which occur in the given number of [steps],
 * given the starting [energyLevels] of the octopi.
 */
fun countFlashes(energyLevels: Map<Point, Int>, steps: Int = 100): Int = when (steps) {
    0 -> 0
    else -> {
        val newEnergyLevels = step(energyLevels)
        flashesInPrecedingStep(newEnergyLevels) + countFlashes(newEnergyLevels, steps - 1)
    }
}

/**
 * Determines how many steps it takes for the octopi to synchronize their flashes,
 * given their starting [energyLevels].
 */
fun synchronize(energyLevels: Map<Point, Int>): Int = when {
    flashesInPrecedingStep(energyLevels) == energyLevels.size -> 0
    else -> 1 + synchronize(step(energyLevels))
}

/**
 * Counts the number of flashes which occurred in the preceding step.
 */
private fun flashesInPrecedingStep(newEnergyLevels: Map<Point, Int>) =
    newEnergyLevels.count { it.value == 0 }

/**
 * Takes a single step.
 */
private fun step(energyLevels: Map<Point, Int>): Map<Point, Int> {
    val result = energyLevels.toMutableMap()

    // First, the energy level of each octopus increases by 1.
    result.entries.forEach { entry -> entry.setValue(entry.value + 1) }

    // Then, any octopus with an energy level greater than 9 flashes.
    val toFlash = result.entries
        .filter { it.value == 10 }
        .map { it.key }
        .toMutableSet()
    while (toFlash.isNotEmpty()) {
        val nextToFlash = toFlash.first()
        toFlash.remove(nextToFlash)
        // This increases the energy level of all adjacent octopuses by 1, including octopuses that are diagonally adjacent.
        nextToFlash.neighboursIncludingDiagonals()
            .filter(result::containsKey)
            .onEach { result[it] = result[it]!! + 1 }
            // If this causes an octopus to have an energy level greater than 9, it also flashes.
            .filter { result[it]!! == 10 }
            .forEach(toFlash::add)
    }

    // Finally, any octopus that flashed during this step has its energy level set to 0
    val flashed = result.entries.filter { 9 < it.value }
        .forEach { it.setValue(0) }

    return result
}
