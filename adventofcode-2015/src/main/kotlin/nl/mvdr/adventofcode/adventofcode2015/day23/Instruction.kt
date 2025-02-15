package nl.mvdr.adventofcode.adventofcode2015.day23

/**
 * Representation of a specific instruction from the puzzle input.
 */
data class Instruction(private val function: (State) -> State) {
    /**
     * Executes this instruction.
     */
    fun execute(state: State) = function.invoke(state)
}

fun parseInstruction(text: String): Instruction {
    val instructionString = text.substring(0, 3)
    val arguments = text.substring(4).split(", ")

    val function = when(instructionString) {
        "hlf" -> { state: State -> state.half(parseRegister(arguments[0])) }
        "tpl" -> { state: State -> state.triple(parseRegister(arguments[0])) }
        "inc" -> { state: State -> state.increment(parseRegister(arguments[0])) }
        "jmp" -> { state: State -> state.jump(arguments[0].toInt()) }
        "jie" -> { state: State -> state.jumpIfEven(parseRegister(arguments[0]), arguments[1].toInt()) }
        "jio" -> { state: State -> state.jumpIfOne(parseRegister(arguments[0]), arguments[1].toInt()) }
        else -> throw IllegalArgumentException("Unknown instruction: $instructionString")
    }
    return Instruction(function)
}
