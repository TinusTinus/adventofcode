package nl.mvdr.adventofcode.adventofcode2015.day08

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = lines.sumOf(String::length) - lines.sumOf(::stringValueLength)

fun stringValueLength(stringLiteral: String): Int {
    var stringValue = stringLiteral

    // Drop the enclosing quotes
    stringValue = stringValue.substring(1, stringValue.length - 1)

    // Lone double-quote characters
    stringValue = stringValue.replace("\\\"", "\"")

    // Hexadecimal representations of characters.
    // For convenience, these are simply replaced by "a" instead of the actual character value.
    // This does not matter for determining the length of the string value.
    stringValue = stringValue.replace(Regex("\\\\x[0-9a-fA-F]{2}"), "a")

    // Backslashes
    stringValue = stringValue.replace("\\\\", "\\")

    logger.debug { "String literal: $stringLiteral, string value: $stringValue" }

    return stringValue.length
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day08-2015.txt")
    logger.info { result }
}
