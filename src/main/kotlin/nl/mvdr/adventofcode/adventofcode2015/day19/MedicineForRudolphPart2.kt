package nl.mvdr.adventofcode.adventofcode2015.day19

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(linesSequence: Sequence<String>): Int {
    val lines = linesSequence.toList()
    val medicineMolecule = lines.last()
    val replacements = lines.dropLast(2).map(::parseReplacement)

    var molecules = setOf("e")
    var steps = 0

    while (!molecules.contains(medicineMolecule)) {
        molecules = replacements.map { it.apply(molecules) }.reduce(Set<String>::union)
        steps++
    }

    return steps
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day19-2015.txt")
    logger.info { result }
}
