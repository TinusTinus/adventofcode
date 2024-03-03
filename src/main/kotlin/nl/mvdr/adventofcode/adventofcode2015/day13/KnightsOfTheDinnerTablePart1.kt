package nl.mvdr.adventofcode.adventofcode2015.day13

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.permutations.Permutations
import kotlin.streams.asSequence

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int {
    val happiness = parseHappiness(lines)

    val names = happiness.keys
        .map { it.first }
        .toSet()

    return Permutations.generatePermutations(names)
        .asSequence()
        .maxOf { calculateTotalHappinessChange(it, happiness) }
}

/**
 * Parses the [lines] of the puzzle input as a map containing changes in happiness.
 *
 * The value
 *   "Alice would gain 54 happiness units by sitting next to Bob."
 * translates to:
 *   happiness[(Alice, Bob)] = 54
 *
 * The value
 *   "Alice would lose 79 happiness units by sitting next to Carol."
 * translates to
 *   happiness[(Alice, Carol)] = -79
 */
private fun parseHappiness(lines: List<String>): Map<Pair<String, String>, Int> {
    val result = mutableMapOf<Pair<String, String>, Int>()
    for (line in lines) {
        val (name, happinessModifier, neighbour) = line.split(" would ", " happiness units by sitting next to ", ".")
        val (signumString, happinessValueString) = happinessModifier.split(" ")
        val signum = when(signumString) {
            "gain" -> 1
            "lose" -> -1
            else -> throw IllegalArgumentException("Unexpected value: '$signumString'")
        }
        result[Pair(name, neighbour)] = signum * happinessValueString.toInt()
    }
    logger.debug { "Happiness: $result" }
    return result
}

/**
 * Calculates the total change in happiness for the given [seating] arrangement,
 * using the given [happiness] mapping.
 */
private fun calculateTotalHappinessChange(seating: List<String>, happiness: Map<Pair<String, String>, Int>): Int =
    seating.mapIndexed { index, name ->
            val rightNeighbour = seating[(index + 1) % seating.size]
            val rightNeighbourHappiness = happiness[Pair(name, rightNeighbour)]!!
            val leftNeighbour = seating[Math.floorMod(index - 1, seating.size)]
            val leftNeighbourHappiness = happiness[Pair(name, leftNeighbour)]!!
            leftNeighbourHappiness + rightNeighbourHappiness
        }.sum()

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day13-2015.txt")
    logger.info { result }
}
