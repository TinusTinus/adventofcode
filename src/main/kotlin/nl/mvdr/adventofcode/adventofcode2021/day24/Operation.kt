package nl.mvdr.adventofcode.adventofcode2021.day24

enum class Operation(val representation: String) {
    INP("inp") {
        override fun perform (state: State, a: Variable, b: Expression?): State {
            val newInput = state.input.drop(1)
            val newVariables = state.variables.toMutableMap()
            newVariables[a] = state.input.first()
            return State(newInput, newVariables)
        }

        override fun evaluate(a: Int, b: Int) = throw UnsupportedOperationException()
    },
    ADD("add") {
        override fun evaluate(a: Int, b: Int) = a + b
    },
    MUL("mul") {
        override fun evaluate(a: Int, b: Int) = a * b
    },
    DIV("div") {
        override fun evaluate(a: Int, b: Int) = a / b
    },
    MOD("mod") {
        override fun evaluate(a: Int, b: Int) = a % b
    },
    EQL("eql") {
        override fun evaluate(a: Int, b: Int) = if (a == b) 1 else 0
    };

    open fun perform(state: State, a: Variable, b: Expression?): State {
        val newVariables = state.variables.toMutableMap()
        newVariables[a] = evaluate(state.variables[a]!!, b!!.getValue(state))
        return state.copy(variables = newVariables)
    }

    protected abstract fun evaluate(a: Int, b: Int): Int
}

/**
 * Parses the string [representation] of an operation.
 */
fun parseOperation(representation: String) = Operation.entries.first { it.representation == representation }