package nl.mvdr.adventofcode.adventofcode2021.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = lines.sumOf(::decodeOutputValue)

/**
 * Counts how many times digits 1, 4, 7, or 8 appear in the given entry's output value.
 */
private fun decodeOutputValue(entry: String): Int {
    val (uniqueSignalPatterns, outputValue) = entry.split(" | ").map { it.split(" ") }

    // Two segments: must be a one
    val onePattern = uniqueSignalPatterns.first { it.length == 2 }

    // Three segments: must be a seven
    val sevenPattern = uniqueSignalPatterns.first { it.length == 3 }

    // Four segments: must be a four
    val fourPattern = uniqueSignalPatterns.first { it.length == 4 }

    // Five segments: could be a two, a three or a five
    val threePattern = uniqueSignalPatterns.first { it.length == 5 && containsAll(it, onePattern) }
    val twoPattern = "herp" // TODO
    val fivePattern = "derp" // TODO

    // Six segments: could be a zero, a six or a nine
    val ninePattern = uniqueSignalPatterns.first { it.length == 6 && containsAll(it, fourPattern) }
    val zeroPattern = uniqueSignalPatterns.first { it.length == 6 && it != ninePattern && containsAll(it, onePattern) }
    val sixPattern = uniqueSignalPatterns.first { it.length == 6 && it != ninePattern && it != zeroPattern }

    // Seven segments: must be a seven
    val eightPattern = uniqueSignalPatterns.first { it.length == 7 }

    return 3 // TODO fix
}

/**
 * Checks whether [lhs] contains all characters in [rhs].
 */
private fun containsAll(lhs: String, rhs: String) = lhs.toCharArray().toList().containsAll(rhs.toCharArray().toList())

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day08-2021.txt")
    logger.info { result }
}
