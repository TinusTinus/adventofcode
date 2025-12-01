package nl.mvdr.adventofcode.adventofcode2025.day01

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.point.Direction
import nl.mvdr.adventofcode.solver.FunctionSolver
import kotlin.math.absoluteValue

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    var dial = 50
    var zeroes = 0
    for (line in lines) {
        val direction = Direction.parse(line.take(1))
        val distance = line.substring(1).toInt()

        println("Direction: $direction, distance: $distance")

        val directionMultiplier = when (direction) {
            Direction.LEFT -> -1
            Direction.RIGHT -> 1
            else -> throw IllegalArgumentException("Unexpected direction: {direction}")
        }

        dial += distance * directionMultiplier

        while (dial < 0) {
            dial += 100
            zeroes++
        }

        while (100 <= dial) {
            dial -= 100
            zeroes++
        }

//        if (dial == 0) {
//            zeroes++
//        }

        println("Dial: $dial, zeroes: $zeroes")

//        // TODO remove?
//        if (dial == 0) {
//            zeroes++
//        } else if (0 < dial) {
//            zeroes += dial / 100
//        } else {
//            zeroes += 1 + dial.absoluteValue / 100
//        }

        dial %= 100
    }

    return zeroes
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day01.txt") // 6603 is too high
    logger.info { result }
}
