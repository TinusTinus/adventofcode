package nl.mvdr.adventofcode.adventofcode2021.day24

/**
 * An expression, to be used as the second argument for an instruction.
 */
interface Expression {
    /**
     * Returns the value of this expression, given a state.
     */
    fun getValue(state: State): Int
}

/**
 * Parses the string representation of a single expression.
 */
fun parseExpression(representation: String) = when {
    representation.first().isLetter() -> VariableExpression(representation)
    else -> IntExpression(representation)
}
