package nl.mvdr.adventofcode.adventofcode2025.day10

import com.microsoft.z3.Context
import com.microsoft.z3.Status

data class Machine(val targetLightsState: IndicatorLightsState, val buttons: List<Button>, val joltageRequirements: JoltageState) {

    fun computeFewestButtonPressesToInit() = computeFewestButtonPressesToReach(targetLightsState, buttons)

    fun computeFewestButtonPressesToRequiredJoltage(): Int {
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

            val result = when (solver.check()) {
                Status.SATISFIABLE -> {
                    buttonConstants.forEach { println("$it = ${solver.model.eval(it, false)}") } // TODO remove or turn into log statement
                    solver.model.eval(totalButtonPresses, false).toString().toInt() // TODO find the minimum value of totalButtonPresses, instead of A value
                }

                else -> throw IllegalStateException("Could not solve for joltage requirements $joltageRequirements")
            }

            println("$result total button presses for $this") // TODO remove or turn into log statement

            return result
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
