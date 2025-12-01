package nl.mvdr.adventofcode.adventofcode2025.day01

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Direction
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    var dial = 50
    var zeroes = 0
    for (line in lines) {
        dial = rotate(dial, line)
        if (dial == 0) {
            zeroes++
        }
    }

    return zeroes
}

private fun rotate(dial: Int, instruction: String): Int {
    val direction = Direction.parse(instruction.take(1))
    val distance = instruction.substring(1).toInt()

    val directionMultiplier = when(direction) {
        Direction.LEFT -> -1
        Direction.RIGHT -> 1
        else -> throw IllegalArgumentException("Unexpected direction: {direction}")
    }

    return (dial + distance * directionMultiplier) % 100
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day01.txt")
    logger.info { result }
}
