package nl.mvdr.adventofcode.adventofcode2015.day20

import kotlin.math.sqrt

/**
 * Returns the lowest house number where at least the given [numberOfPresents] is delivered.
 */
fun lowestHouseNumber(numberOfPresents: Int, presentsPerHouse: Int = 10, housesPerElf: Int? = null) = generateSequence(1, Int::inc)
    .first{ numberOfPresents <= presentsDeliveredAt(it, presentsPerHouse, housesPerElf) }

/**
 * Returns the number of presents delivered to the given [house].
 */
fun presentsDeliveredAt(house: Int, presentsPerHouse: Int, housesPerElf: Int?): Int = when(house) {
    1 -> presentsPerHouse
    else -> sumDivisors(house, housesPerElf) * presentsPerHouse
}

private fun sumDivisors(n: Int, max: Int?): Int {
    require(1 < n)
    val maxDivisor = sqrt(n.toDouble()).toInt()
    var sum = 1
    for (i in 2..maxDivisor) {
        if (n % i == 0) {
            if (true) { // TODO only if elf i delivers to house n
                sum += i
            }
            val d: Int = n / i
            if (d != i && true) { // TODO only if elf d delivers to house n
                sum += d
            }
        }
    }
    if (max == null || n <= max) {
        sum += n
    }
    return sum
}