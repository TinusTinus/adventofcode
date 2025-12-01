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
        val distance = line.substring(1).toInt()

        println("Dial: $dial, direction: $direction, distance: $distance")

        when (direction) {
            Direction.RIGHT -> {
                var remainingDistance = distance
                while (0 < remainingDistance) {
                    if (dial + remainingDistance < 100) {
                        dial += remainingDistance
                        remainingDistance = 0
                    } else {
                        zeroes++
                        remainingDistance -= 100 - dial
                        dial = 0
                    }
                }
            }
            Direction.LEFT -> {
//                if (dial == 0) {
//                    zeroes-- // to avoid counting this pass of 0 twice
//                }

                var remainingDistance = distance
                while (0 < remainingDistance) {
                    if (0 <= dial - remainingDistance) {
                        dial -= remainingDistance
                        remainingDistance = 0
                    } else if (0 < dial) {
                        zeroes++
                        remainingDistance -= dial
                        dial = 0
                    } else if (dial == 0) {

                    }
                }
            }
            else -> throw IllegalArgumentException("Unexpected direction: {direction}")
        }
    }

    return zeroes
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day01.txt") // 6603 is too high
    logger.info { result }
}
