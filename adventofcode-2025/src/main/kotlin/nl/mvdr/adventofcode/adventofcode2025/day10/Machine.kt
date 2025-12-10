package nl.mvdr.adventofcode.adventofcode2025.day10

data class Machine(val targetLightsState: IndicatorLightsState, val buttons: Set<Button>) {
    fun computeFewestButtonPressesToInit() = 3 // TODO implement
}

fun parseMachine(text: String): Machine {
    val parts = text.split(" ")

    val targetLightsState = parseState(parts.first())
    val buttons = parts.subList(1, parts.size - 1).map(::parseButton).toSet()

    return Machine(targetLightsState, buttons)
}
