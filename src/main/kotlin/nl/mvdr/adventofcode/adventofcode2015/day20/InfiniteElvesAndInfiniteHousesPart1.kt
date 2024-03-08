package nl.mvdr.adventofcode.adventofcode2015.day20

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import kotlin.math.sqrt

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = lowestHouseNumber(lines.first().toInt())

/**
 * Returns the lowest house number where at least the given [numberOfPresents] is delivered.
 */
private fun lowestHouseNumber(numberOfPresents: Int) = generateSequence(1, Int::inc).first{ numberOfPresents <= presentsDeliveredAt(it) }

/**
 * Returns the number of presents delivered to the given [house].
 */
fun presentsDeliveredAt(house: Int): Int = when(house) {
    1 -> 10
    else -> sumDivisors(house) * 10
}

private fun sumDivisors(n: Int): Int {
    require(1 < n)
    val maxDivisor = sqrt(n.toDouble()).toInt()
    var sum = 1
    for (i in 2..maxDivisor) {
        if (n % i == 0) {
            sum += i
            val d: Int = n / i
            if (d != i) sum += d
        }
    }
    return sum + n
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day20-2015.txt")
    logger.info { result }
}
