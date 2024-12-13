package nl.mvdr.adventofcode.adventofcode2021.day24

/**
 * A variable, or register, in the arithmetic logic unit.
 */
enum class Variable(val representation: String) {
    W("w"),
    X("x"),
    Y("y"),
    Z("z")
}

/**
 * Parses the string [representation] of a variable.
 */
fun parseVariable(representation: String) = Variable.entries.first { it.representation == representation }