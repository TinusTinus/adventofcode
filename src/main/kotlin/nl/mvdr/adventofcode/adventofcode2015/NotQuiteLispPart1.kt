package nl.mvdr.adventofcode.adventofcode2015

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.LinesSolver
import java.util.stream.Stream

private val logger = KotlinLogging.logger{}

class NotQuiteLispPart1: LinesSolver<Int> {
    override fun solve(lines: Stream<String>?): Int = solve(lines!!.toList())

    private fun solve(lines: List<String>): Int = countInstructions(lines.first())

    private fun countInstructions(text: String): Int {
        return text.toCharArray()
            .map {
                when (it) {
                    '(' -> 1
                    ')' -> -1
                    else -> throw IllegalArgumentException("Unexpected character found in input: $it")
                }
            }
            .sum()
    }
}

fun main() {
    val result = NotQuiteLispPart1().solve("input-day01-2015.txt")

    logger.info { result }
}
