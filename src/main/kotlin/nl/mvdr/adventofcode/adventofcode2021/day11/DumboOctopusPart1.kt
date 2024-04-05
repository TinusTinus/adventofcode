package nl.mvdr.adventofcode.adventofcode2021.day11

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = countFlashes(Point.parse2DMap(lines.toList()) { character -> character.toString().toInt() })

private fun countFlashes(energyLevels: Map<Point, Int>, steps: Int = 100): Int = when (steps) {
    0 -> 0
    else -> {
        // Take a single step and count the flashes
        val newEnergyLevels = energyLevels.toMutableMap()

        // First, the energy level of each octopus increases by 1.
        newEnergyLevels.entries.forEach { entry -> entry.setValue(entry.value + 1) }

        // Then, any octopus with an energy level greater than 9 flashes.
        val toFlash = newEnergyLevels.entries
            .filter { it.value == 10 }
            .map { it.key }
            .toMutableSet()
        while(toFlash.isNotEmpty()) {
            val nextToFlash = toFlash.first()
            toFlash.remove(nextToFlash)
            // This increases the energy level of all adjacent octopuses by 1, including octopuses that are diagonally adjacent.
            nextToFlash.neighboursIncludingDiagonals()
                .filter(newEnergyLevels::containsKey)
                .onEach { newEnergyLevels[it] = newEnergyLevels[it]!! + 1 }
                // If this causes an octopus to have an energy level greater than 9, it also flashes.
                .filter { newEnergyLevels[it]!! == 10 }
                .forEach(toFlash::add)
        }

        // Finally, any octopus that flashed during this step has its energy level set to 0
        val flashed = newEnergyLevels.entries.filter { 9 < it.value }.map { it.key }
        flashed.forEach { newEnergyLevels[it] = 0 }

        flashed.size + countFlashes(newEnergyLevels, steps - 1)
    }
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day11-2021.txt")
    logger.info { result }
}
