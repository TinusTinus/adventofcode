package nl.mvdr.adventofcode.adventofcode2025.day10

data class IndicatorLightsState(val lights: List<Boolean>) {
    fun toggle(button: Button): IndicatorLightsState {
        val newLights = lights.toMutableList()
        button.indexes.forEach { newLights[it] = !newLights[it] }
        return IndicatorLightsState(newLights)
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

fun getInitialState(numberOfLights: Int) = IndicatorLightsState(generateSequence { false }.take(numberOfLights).toList())

fun getPossibleStates(numberOfLights: Int) = getPossibleLights(numberOfLights).map { IndicatorLightsState(it) }

private fun getPossibleLights(numberOfLights: Int): Set<List<Boolean>> =
    when (numberOfLights) {
        0 -> setOf(listOf())
        else -> getPossibleLights(numberOfLights - 1)
            .flatMap { setOf(listOf(true) + it, listOf(false) + it) }
            .toSet()
    }

