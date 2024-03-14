package nl.mvdr.adventofcode.adventofcode2015.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val packages = lines.map(String::toInt).toList()

    val weightPerGroup = packages.sum() / 3

    return generateSequence(1, Int::inc)
        .map { createGroups(packages, weightPerGroup, it) }
        .first { it.isNotEmpty() }
        .minOf(::quantumEntanglement)
}

/**
 * Determines all possible groups out of the given [packages], with the given [groupSize]
 * where the group's total weight equals the given [targetWeight].
 * Note that the given packages must be sorted in increasing weight!
 */
fun createGroups(packages: List<Int>, targetWeight: Int, groupSize: Int): Set<Set<Int>> = when {
    groupSize <= 0 -> throw IllegalArgumentException("Invalid group size: $groupSize")
    groupSize == 1 -> when {
        packages.contains(targetWeight) -> setOf(setOf(targetWeight))
        else -> emptySet()
    }
    packages.isEmpty() -> emptySet()
    targetWeight < packages.first() -> emptySet()
    else -> {
        // Either include the first package in the group, or don't.
        val firstPackage = packages.first()
        val remainingPackages = packages.drop(1)
        createGroups(remainingPackages, targetWeight, groupSize) union
                createGroups(remainingPackages, targetWeight - firstPackage, groupSize - 1).map { it union setOf(firstPackage) }.toSet()
    }
}

private fun quantumEntanglement(group: Set<Int>) = group.reduce { package0, package1 -> package0 * package1 }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day24-2015.txt")
    logger.info { result }
}
