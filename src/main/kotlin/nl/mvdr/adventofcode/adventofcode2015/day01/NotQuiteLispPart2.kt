package nl.mvdr.adventofcode.adventofcode2015.day01

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: List<String>): Int {
    val characters = lines.first().toCharArray()
    var floor = 0
    var position = 0

    while (0 <= floor) {
        when(characters[position]) {
            '(' -> floor++
            ')' -> floor--
            else -> throw IllegalArgumentException("Unexpected character found in puzzle input")
        }
        position++
    }
    return position
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day01-2015.txt")
    logger.info { result }
}
