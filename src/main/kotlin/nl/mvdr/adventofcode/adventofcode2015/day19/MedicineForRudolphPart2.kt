package nl.mvdr.adventofcode.adventofcode2015.day19

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val medicineMolecule = lines.last()
    // From observing the input:
    return medicineMolecule.count { it.isUpperCase() } -
            countOccurrences(medicineMolecule, "Rn") -
            countOccurrences(medicineMolecule, "Ar") -
            2 * countOccurrences(medicineMolecule, "Y") - 1
}

/**
 * Counts the occurrences of the given (nonempty) [substring] in the given [string].
 */
private fun countOccurrences(string: String, substring: String): Int = when {
    string == "" -> 0
    else -> countOccurrences(string.substring(1), substring) + when {
        string.startsWith(substring) -> 1
        else -> 0
    }
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day19-2015.txt")
    logger.info { result }
}
