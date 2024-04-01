package nl.mvdr.adventofcode.adventofcode2021.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>) = lines.sumOf(::decodeOutputValue)

private fun decodeOutputValue(entry: String): Int {
    val (uniqueSignalPatterns, outputValue) = entry.split(" | ")
        .map { it.split(" ").map(::SignalPattern) }
    return Decoder(uniqueSignalPatterns).decode(outputValue)
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day08-2021.txt")
    logger.info { result }
}
