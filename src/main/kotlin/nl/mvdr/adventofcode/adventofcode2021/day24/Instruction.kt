package nl.mvdr.adventofcode.adventofcode2021.day24

data class Instruction(val operation: Operation, val a: Variable, val b: Expression?) {
    fun perform(state: State): State = operation.perform(state, a, b)
}


/**
 * Parses the string [representation] of a single instruction.
 */
fun parseInstruction(representation: String): Instruction {
    val parts = representation.split(" ")
    val operation = parseOperation(parts[0])
    val a = parseVariable(parts[1])
    val b = when (parts.size) {
        2 -> null
        3 -> parseExpression(parts[2])
        else -> throw IllegalArgumentException("Unexpected number of parts in $representation: " + parts.size)
    }
    return Instruction(operation, a, b)
}
