package nl.mvdr.adventofcode.adventofcode2021.day24

data class IntExpression(val value: Int): Expression {
    constructor(value: String) : this(value.toInt())

    override fun getValue(state: State) = value
}
