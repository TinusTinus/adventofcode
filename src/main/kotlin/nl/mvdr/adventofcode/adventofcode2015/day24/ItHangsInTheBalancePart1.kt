package nl.mvdr.adventofcode.adventofcode2015.day24

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val packages = lines.map(String::toInt).toList()

    val weightPerGroup = packages.sum() / 3

    val groups = createGroups(packages, weightPerGroup)

    val firstGroup = groups.sortedWith(
        compareBy { group: Set<Int> -> group.size }
            .thenComparing(compareBy { quantumEntanglement(it) } ))
        .first()

    return quantumEntanglement(firstGroup)
}

/**
 * Determines all possible groups out of the given [packages],
 * where the group's total weight equals the given [targetWeight].
 */
fun createGroups(packages: List<Int>, targetWeight: Int): Set<Set<Int>> = when {
    // Note: this function does not assume anything about the order of the packages.
    // The input is actually sorted by increasing package weight.
    // This can be used to cut the computation short in order to improve performance.
    targetWeight == 0 -> setOf(emptySet())
    targetWeight < 0 || packages.isEmpty() -> emptySet()
    else -> {
        val firstPackage = packages.first()
        val remainingPackages = packages.drop(1)

        // Either include the first package in the group, or don't.
        createGroups(remainingPackages, targetWeight) union
        createGroups(remainingPackages, targetWeight - firstPackage).map { it union setOf(firstPackage) }.toSet()
    }
}

private fun quantumEntanglement(group: Set<Int>) = group.reduce { package0, package1 -> package0 * package1 }

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day24-2015.txt")
    logger.info { result }
}
