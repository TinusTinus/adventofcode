package nl.mvdr.adventofcode.adventofcode2015.day23

data class Program(private val instructions: List<Instruction>) {
    constructor(lines: Sequence<String>) : this(lines.map(::parseInstruction).toList())

    /**
     * Executes this program, starting with the initial state.
     */
    fun execute(): State {
        var state = State()
        while (0 <= state.instructionPointer && state.instructionPointer < instructions.size) {
            state = instructions[state.instructionPointer].execute(state)
        }
        return state
    }
}

