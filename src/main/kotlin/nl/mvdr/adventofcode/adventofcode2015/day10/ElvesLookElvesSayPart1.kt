package nl.mvdr.adventofcode.adventofcode2015.day10

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int = lookAndSay(lines.first(), 40).length

fun lookAndSay(input: String, times: Int): String {
    var result = input
    for (i in 0 until times) {
        result = lookAndSay(result)
    }
    return result
}

fun lookAndSay(input: String): String = when {
    input.isEmpty() -> input
    else -> {
        val firstCharacter = input.first()
        var count = input.indexOfFirst { firstCharacter != it }
        if (count < 0) {
            count = input.length
        }
        "$count$firstCharacter" + lookAndSay(input.substring(count))
    }
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day10-2015.txt")
    logger.info { result }
}
