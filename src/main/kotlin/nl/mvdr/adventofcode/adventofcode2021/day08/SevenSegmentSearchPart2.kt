package nl.mvdr.adventofcode.adventofcode2021.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = lines.sumOf(::decodeOutputValue)

/**
 * Counts how many times digits 1, 4, 7, or 8 appear in the given entry's output value.
 */
private fun decodeOutputValue(entry: String): Int {
    val (uniqueSignalPatterns, outputValue) = entry.split(" | ")
        .map { it.split(" ").map { pattern -> pattern.toCharArray().toSet() } }

    val patterns = mutableMapOf<Int, Set<Char>>()

    // Two segments: must be a one
    patterns[1] = uniqueSignalPatterns.first { it.size == 2 }

    // Four segments: must be a four
    patterns[4] = uniqueSignalPatterns.first { it.size == 4 }

    // Three segments: must be a seven
    patterns[7] = uniqueSignalPatterns.first { it.size == 3 }

    // Seven segments: must be an eight
    patterns[8] = uniqueSignalPatterns.first { it.size == 7 }

    // Six segments: could be a zero, a six or a nine
    patterns[9] = uniqueSignalPatterns.first { it.size == 6 && it.containsAll(patterns[4]!!) }
    patterns[0] = uniqueSignalPatterns.first { it.size == 6 && it != patterns[9] && it.containsAll(patterns[1]!!) }
    patterns[6] = uniqueSignalPatterns.first { it.size == 6 && it != patterns[9] && it != patterns[0] }

    // Five segments: could be a two, a three or a five
    patterns[3] = uniqueSignalPatterns.first { it.size == 5 && it.containsAll(patterns[1]!!) }
    patterns[5] = uniqueSignalPatterns.first { it.size == 5 && it != patterns[3] && patterns[6]!!.containsAll(it) }
    patterns[2] = uniqueSignalPatterns.first { it.size == 5 && it != patterns[3] && it != patterns[5] }

    val decoder = patterns.entries.associate { (digit, pattern) -> pattern to digit }

    return outputValue.map { decoder[it]!! }.joinToString("").toInt()
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day08-2021.txt")
    logger.info { result }
}
