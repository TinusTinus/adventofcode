package nl.mvdr.adventofcode.adventofcode2025.day10

data class JoltageState(val joltages: List<Int>): State<JoltageState> {
    override fun update(button: Button): JoltageState {
        val newJoltages = joltages.toMutableList()
        button.indexes.forEach { newJoltages[it]++ }
        return JoltageState(newJoltages)
    }

    override fun getInitialState() = JoltageState(generateSequence { 0 }.take(joltages.size).toList())

    override fun getPossibleStates() = getPossibleJoltages(joltages.size)
        .map { JoltageState(it) }
        .toSet()

    private fun getPossibleJoltages(numberOfJoltages: Int): Set<List<Int>> =
        when (numberOfJoltages) {
            0 -> setOf(listOf())
            else -> getPossibleJoltages(numberOfJoltages - 1)
                .flatMap { (0 .. joltages[numberOfJoltages - 1]).map { joltage -> it + listOf(joltage) } }
                .toSet()
        }
}

fun parseJoltageRequirements(text: String): JoltageState {
    if (!text.startsWith("{") || !text.endsWith("}")) {
        throw IllegalArgumentException("Joltage requirements must be enclosed in curly brackets. Given value: $text")
    }

    val joltages = text.substring(1, text.length - 1)
        .split(",")
        .map(String::toInt)
        .toList()

    return JoltageState(joltages)
}
