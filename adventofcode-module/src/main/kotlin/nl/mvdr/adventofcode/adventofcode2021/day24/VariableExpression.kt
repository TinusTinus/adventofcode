package nl.mvdr.adventofcode.adventofcode2021.day24

import com.microsoft.z3.Context

data class VariableExpression(val variable: Variable) : Expression {
    constructor(variable: String) : this(parseVariable(variable))

    override fun getValue(state: State, context: Context) = state.variables[variable]!!
}
