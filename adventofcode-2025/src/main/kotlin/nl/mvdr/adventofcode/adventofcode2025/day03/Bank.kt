package nl.mvdr.adventofcode.adventofcode2025.day03

import kotlin.math.pow

data class Bank(val batteryJoltages: List<Int>) {
    fun computeMaxJoltage(digits: Int) = computeMaxJoltage(batteryJoltages, digits)
}

fun parseBank(line: String) = Bank(line.toList().map(Char::digitToInt))

private fun computeMaxJoltage(batteryJoltages: List<Int>, digits: Int): Long =
    when (digits) {
        0 -> 0
        else -> {
            val firstDigit = batteryJoltages.dropLast(digits - 1).max()
            firstDigit * (10).toDouble().pow(digits - 1).toLong() +
                    computeMaxJoltage(batteryJoltages.drop(batteryJoltages.indexOf(firstDigit) + 1), digits - 1)
        }
    }