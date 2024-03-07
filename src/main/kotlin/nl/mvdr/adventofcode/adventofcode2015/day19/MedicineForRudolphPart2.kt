package nl.mvdr.adventofcode.adventofcode2015.day19

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

private const val ELECTRON = "e"

fun solvePart2(linesSequence: Sequence<String>): Int {
    // Parse the input
    val lines = linesSequence.toList()
    val medicineMolecule = lines.last()
    val replacements = lines.dropLast(2).map(::parseReplacement)

    // A naive brute-force solution would not perform very well.

    // Some observations of the input:
    // - The replacements do not include cycles (x => ... => x)
    // - Each replacement causes the text to become longer or stay the same length.

    // Instead of starting with an electron and generating the medicine molecule,
    // let's work backwards from the medicine molecule instead.
    // We also attempt to reduce the length of the string by the largest amount possible in each step,
    // to get to a single-character value "e" as quickly as possible.
    val reverseReplacements = replacements.map(Replacement::reverse).sortedWith(compareBy { it.to.length - it.from.length })
    return countSteps(medicineMolecule, ELECTRON, reverseReplacements)!!
}

/**
 * Finds the smallest number of steps in which [sourceMolecule] can be transformed into [targetMolecule],
 * by applying any of the given [replacements].
 */
private fun countSteps(sourceMolecule: String, targetMolecule: String, replacements: List<Replacement>): Int? = when(sourceMolecule) {
    targetMolecule -> 0
    else -> replacements.asSequence()
        .flatMap { it.apply(sourceMolecule) }
        .map { countSteps(it, targetMolecule, replacements) }
        .filterNotNull()
        .map { it + 1 }
        .firstOrNull()
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day19-2015.txt")
    logger.info { result }
}
