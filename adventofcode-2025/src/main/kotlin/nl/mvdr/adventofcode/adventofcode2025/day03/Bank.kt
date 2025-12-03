package nl.mvdr.adventofcode.adventofcode2025.day03

data class Bank(val batteryJoltages: List<Int>) {
    fun computeMaxJoltage(): Int {
        val firstDigit = batteryJoltages.dropLast(1).max()
        val secondDigit = batteryJoltages.drop(batteryJoltages.indexOf(firstDigit) + 1).max()
        return firstDigit * 10 + secondDigit
    }
}

fun parseBank(line: String) = Bank(line.toList().map(Char::digitToInt))

