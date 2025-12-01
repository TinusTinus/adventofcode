package nl.mvdr.adventofcode.adventofcode2025.day01

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Direction
import nl.mvdr.adventofcode.solver.FunctionSolver
import kotlin.math.absoluteValue
import kotlin.math.min

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    var dial = 50
    var zeroes = 0
    for (line in lines) {
        val direction = Direction.parse(line.take(1))
        val directionMultiplier = when(direction) {
            Direction.LEFT -> -1
            Direction.RIGHT -> 1
            else -> throw IllegalArgumentException("Unexpected direction: {direction}")
        }
        val distance = line.substring(1).toInt() * directionMultiplier

        val previousDial = dial
        zeroes += (distance.absoluteValue / 100)
        dial = (dial + distance) % 100
        if (previousDial != 0 && dial != 0) {
            if ((distance < 0 && previousDial < dial) ||
                    (0 < distance && dial < previousDial)
            ) {
                zeroes += 1
            }
        }
        if (dial == 0) {
            zeroes += 1
        }
    }

    return zeroes

}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day01.txt") // 6603 is too high
    logger.info { result }
}
