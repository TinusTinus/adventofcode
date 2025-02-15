package nl.mvdr.adventofcode.adventofcode2015.day10

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger{}

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