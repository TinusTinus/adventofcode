package nl.mvdr.adventofcode.adventofcode2015.day20

import kotlin.math.sqrt

/**
 * Returns the lowest house number where at least the given [numberOfPresents] is delivered,
 * given that each elf delivers the given number of [presentsPerHouse].
 * By default, each elf visits infinitely many houses. This can be limited by passing in a non-null [housesPerElf] value.
 */
fun lowestHouseNumber(numberOfPresents: Int, presentsPerHouse: Int, housesPerElf: Int? = null) = generateSequence(1, Int::inc)
    .first{ numberOfPresents <= presentsDeliveredAt(it, presentsPerHouse, housesPerElf) }

/**
 * Returns the number of presents delivered to the given [house].
 */
fun presentsDeliveredAt(house: Int, presentsPerHouse: Int, housesPerElf: Int?): Int = when(house) {
    1 -> presentsPerHouse
    else -> sumDivisors(house, housesPerElf) * presentsPerHouse
}

/**
 * Computes the sum of the divisors of the given number [n].
 * A divisor x is only included if n / x <= [max], if a maximum is provided.
 */
private fun sumDivisors(n: Int, max: Int?): Int {
    require(1 < n)
    val maxDivisor = sqrt(n.toDouble()).toInt()
    var sum = 0
    for (i in 1..maxDivisor) {
        if (n % i == 0) {
            val d: Int = n / i
            if (max == null || d <= max) {
                sum += i
            }
            if (d != i && (max == null || i <= max)) {
                sum += d
            }
        }
    }
    return sum
}