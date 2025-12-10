package nl.mvdr.adventofcode.adventofcode2025.day10

data class IndicatorLightsState(val lights: List<Boolean>)

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
