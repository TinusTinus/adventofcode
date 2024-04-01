package nl.mvdr.adventofcode.adventofcode2021.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = lines.sumOf(::decodeOutputValue)

/**
 * Decodes the output value in the given [entry].
 */
private fun decodeOutputValue(entry: String): Int {
    // Parse the entry.
    // Each digit is represented as a set of characters,
    // where each character represents a segment of the display.
    val (uniqueSignalPatterns, outputValue) = entry.split(" | ")
        .map { it.split(" ").map { pattern -> pattern.toCharArray().toSet() } }

    val decoder = createDecoder(uniqueSignalPatterns)

    val outputValueDigits = outputValue.map { decoder[it]!! }
    return outputValueDigits.joinToString("").toInt()
}

/**
 * Creates a decoder, based on the given [uniqueSignalPatterns].
 * The decoder maps a signal pattern to the corresponding digit.
 */
private fun createDecoder(uniqueSignalPatterns: List<Set<Char>>): Map<Set<Char>, Int> =
    findPatterns(uniqueSignalPatterns).entries.associate { (digit, pattern) -> pattern to digit }

/**
 * Finds, for each digit, the corresponding unique signal pattern.
 */
private fun findPatterns(uniqueSignalPatterns: List<Set<Char>>): Map<Int, Set<Char>> {
    val result = mutableMapOf<Int, Set<Char>>()

    // Two segments: must be a one
    result[1] = uniqueSignalPatterns.first { it.size == 2 }

    // Four segments: must be a four
    result[4] = uniqueSignalPatterns.first { it.size == 4 }

    // Three segments: must be a seven
    result[7] = uniqueSignalPatterns.first { it.size == 3 }

    // Seven segments: must be an eight
    result[8] = uniqueSignalPatterns.first { it.size == 7 }

    // Six segments: could be a zero, a six or a nine
    result[9] = uniqueSignalPatterns.first { it.size == 6 && it.containsAll(result[4]!!) }
    result[0] = uniqueSignalPatterns.first { it.size == 6 && it != result[9] && it.containsAll(result[1]!!) }
    result[6] = uniqueSignalPatterns.first { it.size == 6 && it != result[9] && it != result[0] }

    // Five segments: could be a two, a three or a five
    result[3] = uniqueSignalPatterns.first { it.size == 5 && it.containsAll(result[1]!!) }
    result[5] = uniqueSignalPatterns.first { it.size == 5 && it != result[3] && result[6]!!.containsAll(it) }
    result[2] = uniqueSignalPatterns.first { it.size == 5 && it != result[3] && it != result[5] }

    return result
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day08-2021.txt")
    logger.info { result }
}
