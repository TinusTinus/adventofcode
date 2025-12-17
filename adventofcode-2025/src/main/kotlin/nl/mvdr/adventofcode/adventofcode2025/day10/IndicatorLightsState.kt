package nl.mvdr.adventofcode.adventofcode2025.day10

data class IndicatorLightsState(val lights: List<Boolean>): State<IndicatorLightsState> {
    override fun update(button: Button): IndicatorLightsState {
        val newLights = lights.toMutableList()
        button.indexes.forEach { newLights[it] = !newLights[it] }
        return IndicatorLightsState(newLights)
    }

    override fun getInitialState() = IndicatorLightsState(generateSequence { false }.take(lights.size).toList())

    override fun getPossibleStates() = getPossibleLights(lights.size)
        .map { IndicatorLightsState(it) }
        .toSet()

    private fun getPossibleLights(numberOfLights: Int): Set<List<Boolean>> =
        when (numberOfLights) {
            0 -> setOf(listOf())
            else -> getPossibleLights(numberOfLights - 1)
                .flatMap { setOf(listOf(true) + it, listOf(false) + it) }
                .toSet()
        }
}

fun parseState(text: String): IndicatorLightsState {
    if (!text.startsWith("[") || !text.endsWith("]")) {
        throw IllegalArgumentException("An indicator light diagram must be enclosed in square brackets. Given value: $text")
    }

    val lights = text.substring(1, text.length - 1)
        .map { when(it) {
            '.' -> false
            '#' -> true
            else -> throw IllegalArgumentException("Unexpected indicator light value: $it")
        } }

    return IndicatorLightsState(lights)
}


