package nl.mvdr.adventofcode.adventofcode2015.day20

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import org.apache.commons.math3.primes.Primes
import javax.swing.text.html.HTML.Attribute.N



private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int = lowestHouseNumber(lines.first().toInt())

/**
 * Returns the lowest house number where at least the given [numberOfPresents] is delivered.
 */
private fun lowestHouseNumber(numberOfPresents: Int) = generateSequence(1, Int::inc).first{ numberOfPresents <= presentsDeliveredAt(it) }

/**
 * Returns the number of presents delivered to the given [house].
 */
fun presentsDeliveredAt(house: Int): Int = sumDivisors(house) * 10

private fun sumDivisors(n: Int) = when {
    n < 1 -> throw IllegalArgumentException("n must be positive but was $n")
    n == 1 -> 1
    else -> {
        val primeFactorization = Primes.primeFactors(n)
        var total = 1
        for (prime in primeFactorization) {
            var factor = 1
            for (i in 0 until n / prime ) { // TODO this is incorrect
                factor *= prime
                factor += 1
            }
            total *= factor
        }
        total
    }
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day20-2015.txt")
    logger.info { result }
}
