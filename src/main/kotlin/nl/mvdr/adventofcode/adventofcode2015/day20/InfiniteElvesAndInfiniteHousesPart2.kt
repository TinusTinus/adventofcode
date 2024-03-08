package nl.mvdr.adventofcode.adventofcode2015.day20

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import kotlin.math.sqrt

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int = lowestHouseNumber(lines.first().toInt())

/**
 * Returns the lowest house number where at least the given [numberOfPresents] is delivered.
 */
private fun lowestHouseNumber(numberOfPresents: Int) = generateSequence(1, Int::inc).first{ numberOfPresents <= presentsDeliveredAtPart2(it) }

/**
 * Returns the number of presents delivered to the given [house].
 */
private fun presentsDeliveredAtPart2(house: Int): Int = when(house) {
    1 -> 11
    else -> sumDivisors(house) * 11
}

private fun sumDivisors(n: Int): Int {
    require(1 < n)
    val maxDivisor = sqrt(n.toDouble()).toInt()
    var sum = 0
    for (i in 1..maxDivisor) {
        if (n % i == 0) {
            val d: Int = n / i
            if (d <= 50) {
                sum += i
            }
            if (d != i && i <= 50) {
                sum += d
            }
        }
    }
    return sum + n
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day20-2015.txt")
    logger.info { result }
    //  693000 is too low
    //  831600 is the minimum
    // 3272727 is too high
}
