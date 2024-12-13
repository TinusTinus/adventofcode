package nl.mvdr.adventofcode.adventofcode2021.day24

import com.microsoft.z3.Context

data class IntExpression(val value: Int): Expression {
    constructor(value: String) : this(value.toInt())

    override fun getValue(state: State, context: Context) = context.mkBV(value, 64)!!
}
