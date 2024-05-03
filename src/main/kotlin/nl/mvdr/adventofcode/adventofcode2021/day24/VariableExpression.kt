package nl.mvdr.adventofcode.adventofcode2021.day24

data class VariableExpression(val variable: Variable) : Expression {
    constructor(variable: String) : this(parseVariable(variable))

    override fun getValue(state: State) = state.variables[variable]!!
}
