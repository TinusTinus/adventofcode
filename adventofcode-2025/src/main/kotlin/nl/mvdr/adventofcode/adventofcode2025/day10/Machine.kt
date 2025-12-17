package nl.mvdr.adventofcode.adventofcode2025.day10

import com.microsoft.z3.Context
import com.microsoft.z3.Status

data class Machine(val targetLightsState: IndicatorLightsState, val buttons: List<Button>, val joltageRequirements: JoltageState) {

    fun computeFewestButtonPressesToInit() = computeFewestButtonPressesToReach(targetLightsState, buttons)

    fun computeFewestButtonPressesToRequiredJoltage(): Int {
        var result = computeButtonPressesToRequiredJoltage()!!

        var nextResult = computeButtonPressesToRequiredJoltage(result - 1)
        while (nextResult != null) {
            result = nextResult
            nextResult = computeButtonPressesToRequiredJoltage(result - 1)
        }

        return result
    }

    /**
     * Note that this method is not guaranteed to return the minimum number of button presses.
     * It just returns a valid number of button presses, which is at most equal to the given maximum,
     * or null if such a number does not exist.
     */
    private fun computeButtonPressesToRequiredJoltage(max: Int = Int.MAX_VALUE): Int? {
        Context().use { context ->
            val solver = context.mkSolver()

            (0 ..< joltageRequirements.joltages.size).forEach { joltageIndex ->
                val joltageRequirement = context.mkInt(joltageRequirements.joltages[joltageIndex])
                val buttonConstants = (0 ..< buttons.size)
                    .filter { buttonIndex -> buttons[buttonIndex].indexes.contains(joltageIndex) }
                    .map { buttonIndex -> "b$buttonIndex" }
                    .map(context::mkIntConst)
                    .toTypedArray()
                solver.add(context.mkEq(joltageRequirement, context.mkAdd(*buttonConstants)))
            }

            val buttonConstants = (0 ..< buttons.size)
                .map { buttonIndex -> "b$buttonIndex" }
                .map(context::mkIntConst)
                .toTypedArray()
            buttonConstants.forEach { buttonConstant -> solver.add(context.mkLe(context.mkInt(0), buttonConstant)) }

            val totalButtonPresses = context.mkIntConst("totalButtonPresses")
            solver.add(context.mkEq(totalButtonPresses, context.mkAdd(*buttonConstants)))
            solver.add(context.mkLe(totalButtonPresses, context.mkInt(max)))

            return when (solver.check()) {
                Status.SATISFIABLE -> solver.model.eval(totalButtonPresses, false).toString().toInt()
                Status.UNSATISFIABLE -> null
                Status.UNKNOWN -> throw IllegalStateException("Status unknown")
            }
        }
    }
}

fun parseMachine(text: String): Machine {
    val parts = text.split(" ")

    val targetLightsState = parseState(parts.first())
    val buttons = parts.subList(1, parts.size - 1).map(::parseButton)
    val joltageRequirements = parseJoltageRequirements(parts.last())

    return Machine(targetLightsState, buttons, joltageRequirements)
}
