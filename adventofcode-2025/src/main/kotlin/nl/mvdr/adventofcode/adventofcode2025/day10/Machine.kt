package nl.mvdr.adventofcode.adventofcode2025.day10

data class Machine(val targetLightsState: IndicatorLightsState, val buttons: Set<Button>, val joltageRequirements: JoltageState) {

    fun computeFewestButtonPressesToInit() = computeFewestButtonPressesToReach(targetLightsState, buttons)

    fun computeFewestButtonPressesToRequiredJoltage() = computeFewestButtonPressesToReach(joltageRequirements, buttons)
}

fun parseMachine(text: String): Machine {
    val parts = text.split(" ")

    val targetLightsState = parseState(parts.first())
    val buttons = parts.subList(1, parts.size - 1).map(::parseButton).toSet()
    val joltageRequirements = parseJoltageRequirements(parts.last())

    return Machine(targetLightsState, buttons, joltageRequirements)
}
