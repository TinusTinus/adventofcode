package nl.mvdr.adventofcode.adventofcode2015.day19

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

private const val ELECTRON = "e"

fun solvePart2(linesSequence: Sequence<String>): Int {
    val lines = linesSequence.toList()
    val medicineMolecule = lines.last()
    val replacements = lines.dropLast(2).map(::parseReplacement)
    val reverseReplacements = replacements.map(Replacement::reverse)

    var molecule = medicineMolecule
    var steps = 0
    while (molecule != ELECTRON) {
        // TODO apply the matching reverse replacement which loses the largest number of characters
        steps++
        logger.info { steps }
    }
    return steps
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day19-2015.txt")
    logger.info { result }
}
