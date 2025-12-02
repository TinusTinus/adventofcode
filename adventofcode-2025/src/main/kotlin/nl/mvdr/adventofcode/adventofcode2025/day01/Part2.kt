package nl.mvdr.adventofcode.adventofcode2025.day01

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Direction
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    var dial = 50
    var zeroes = 0
    for (instruction in lines) {
        var distance = instruction.substring(1).toInt()
        val direction = when(Direction.parse(instruction.take(1))) {
            Direction.LEFT -> -1
            Direction.RIGHT -> 1
            else -> throw IllegalArgumentException("Unexpected direction: {direction}")
        }

        // Let's just brute force it.
        // I'm sure this can be done by cleverly using div and mod, but the edge cases seem tricky.
        while (0 < distance) {
            dial = (dial + direction) % 100
            if (dial == 0) {
                zeroes++
            }
            distance--
        }
    }

    return zeroes
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day01.txt")
    logger.info { result }
}
