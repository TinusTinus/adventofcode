package nl.mvdr.adventofcode.adventofcode2021.day24

enum class Operation(val representation: String) {
    INP("inp"),
    ADD("add"),
    MUL("mul"),
    DIV("div"),
    MOD("mod"),
    EQL("eql")
}

/**
 * Parses the string [representation] of an operation.
 */
fun parseOperation(representation: String) = Operation.entries.first { it.representation == representation }