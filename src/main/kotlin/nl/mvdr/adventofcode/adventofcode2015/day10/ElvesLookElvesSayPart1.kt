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

fun lookAndSay(input: String): String {
    var result = ""
    var currentCharacter = input.first()
    var count = 1
    for (character in input.substring(1)) {
        if (character == currentCharacter) {
            count++
        } else {
            result += "$count$currentCharacter"
            count = 1
            currentCharacter = character
        }
    }
    result += "$count$currentCharacter"
    logger.debug { "$input becomes $result" }
    return result
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day10-2015.txt")
    logger.info { result }
}
