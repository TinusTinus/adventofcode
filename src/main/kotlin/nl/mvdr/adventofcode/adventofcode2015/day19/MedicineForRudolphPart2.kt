package nl.mvdr.adventofcode.adventofcode2015.day19

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

private const val ELECTRON = "e"

fun solvePart2(linesSequence: Sequence<String>): Int {
    val lines = linesSequence.toList()
    val medicineMolecule = lines.last()
    val replacements = lines.dropLast(2).map(::parseReplacement)

    // instead of starting with an electron and generating the medicine molecule,
    // let's work backwards from the medicine molecule instead.

    val reverseReplacements = replacements.map(Replacement::reverse)
    return countSteps(medicineMolecule, ELECTRON, reverseReplacements)
}

/**
 * Finds the smallest number of steps in which [sourceMolecule] can be transformed into [targetMolecule],
 * by applying any of the given [replacements].
 */
private fun countSteps(sourceMolecule: String, targetMolecule: String, replacements: List<Replacement>): Int {
    var molecules = setOf(sourceMolecule)
    var steps = 0
    while (!molecules.contains(targetMolecule)) {
        molecules = replacements.map { it.apply(molecules) }.reduce(Set<String>::union)
        steps++
        logger.info { steps }
    }
    return steps
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day19-2015.txt")
    logger.info { result }
}
