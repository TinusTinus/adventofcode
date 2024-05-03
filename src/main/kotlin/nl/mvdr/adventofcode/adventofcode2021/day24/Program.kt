package nl.mvdr.adventofcode.adventofcode2021.day24

data class Program(val instructions: List<Instruction>) {
    /**
     * Parses the [lines] from the puzzle input as a program.
     */
    constructor(lines: Sequence<String>) : this(lines.map(::parseInstruction).toList())

    fun execute(input: List<Int>) = execute(State(input))

    private fun execute(state: State): State = when {
        instructions.isEmpty() -> state
        else -> Program(instructions.drop(1)).execute(instructions.first().perform(state))
    }
}
