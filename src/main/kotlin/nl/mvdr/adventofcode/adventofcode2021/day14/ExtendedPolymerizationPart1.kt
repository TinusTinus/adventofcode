package nl.mvdr.adventofcode.adventofcode2021.day14

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(linesSequence: Sequence<String>): Int {
    val lines = linesSequence.toList()
    val polymerTemplate = lines[0]
    if (lines[1].isNotEmpty()) {
        throw IllegalArgumentException("Expected second line to be empty in the input, but was: " + lines[1])
    }
    val insertionRules = parseInsertionRules(lines.drop(2))

    val polymer = polymerize(polymerTemplate, insertionRules)

    val occurrenceCounts = countElementOccurrences(polymer).values
    return occurrenceCounts.max() - occurrenceCounts.min()
}

private fun parseInsertionRules(lines: List<String>): Map<String, String> {
    val insertionRules = mutableMapOf<String, String>()
    for (insertionRuleString in lines) {
        val (pair, insertion) = insertionRuleString.split(" -> ")
        insertionRules[pair] = insertion
    }
    return insertionRules
}

private fun polymerize(polymer: String, insertionRules: Map<String, String>, steps: Int = 10): String = when (steps) {
    0 -> polymer
    else -> polymerize(pairInsertion(polymer, insertionRules), insertionRules, steps - 1)
}

/**
 * Performs a single pair insertion step.
 * The [polymer] parameter is a string representation of the current form of the polymer.
 * For example: "NNCB" or "NBBBCNCCNBBNBNBBCHBHHBCHB".
 * The [insertionRules] map contains as its key the pair of elements (for example "AB"),
 * and as its value the element to be inserted (for example, "C").
 */
private fun pairInsertion(polymer: String, insertionRules: Map<String, String>): String {
    var result = ""
    for (i in 0 until polymer.length - 1) {
        result += polymer[i]
        result += insertionRules[polymer.substring(i until i + 2)] ?: ""
    }
    result += polymer.last()
    logger.debug { "$polymer -> $result" }
    return result
}

private fun countElementOccurrences(polymer: String): Map<Char, Int> {
    val result = mutableMapOf<Char, Int>()
    for (element in polymer) {
        result.putIfAbsent(element, 0)
        result[element] = result[element]!! + 1
    }
    return result
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day14-2021.txt")
    logger.info { result }
}
