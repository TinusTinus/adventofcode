package nl.mvdr.adventofcode.adventofcode2021.day24

import com.microsoft.z3.Context
import com.microsoft.z3.IntExpr
import com.microsoft.z3.Optimize

data class Program(private val instructions: List<Instruction>) {
    /**
     * Parses the [lines] from the puzzle input as a program.
     */
    constructor(lines: Sequence<String>) : this(lines.map(::parseInstruction).toList())

    fun execute(input: List<IntExpr>, optimize: Optimize, context: Context): State = execute(State(input, context), optimize, context)

    private fun execute(state: State, optimize: Optimize, context: Context): State = when {
        instructions.isEmpty() -> state
        else -> Program(instructions.drop(1)).execute(instructions.first().perform(state, optimize, context), optimize, context)
    }
}
