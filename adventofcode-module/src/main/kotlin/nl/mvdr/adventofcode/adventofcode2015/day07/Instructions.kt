package nl.mvdr.adventofcode.adventofcode2015.day07

fun parseInstructions(lines: Sequence<String>): MutableMap<String, Expression> {
    val instructions = mutableMapOf<String, Expression>()
    lines.forEach { parseInstruction(it, instructions) }
    return instructions
}
private fun parseInstruction(text: String, instructions: MutableMap<String, Expression>) {
    val (expressionString, wire) = text.split(" -> ")
    val expression = parseExpression(expressionString)
    instructions[wire] = expression
}

private fun parseExpression(expressionString: String): Expression {
    val expression = when {
        expressionString.all(Char::isDigit) -> SignalExpression(expressionString.toInt())
        expressionString.startsWith("NOT") -> NotExpression(parseExpression(expressionString.substring(4)))
        expressionString.contains("AND") -> {
            val (lhs, rhs) = expressionString.split(" AND ").map(::parseExpression)
            AndExpression(lhs, rhs)
        }
        expressionString.contains("OR") -> {
            val (lhs, rhs) = expressionString.split(" OR ").map(::parseExpression)
            OrExpression(lhs, rhs)
        }
        expressionString.contains("LSHIFT") -> {
            val (lhs, rhs) = expressionString.split(" LSHIFT ").map(::parseExpression)
            LShiftExpression(lhs, rhs)
        }
        expressionString.contains("RSHIFT") -> {
            val (lhs, rhs) = expressionString.split(" RSHIFT ").map(::parseExpression)
            RShiftExpression(lhs, rhs)
        }
        else -> PassExpression(expressionString)
    }
    return expression
}

fun evaluate(instructions: MutableMap<String, Expression>, wire: String): Int {
    val instruction = instructions[wire]!!
    val result = instruction.evaluate(instructions)
    if (instruction !is SignalExpression) {
        // Cache the result
        instructions[wire] = SignalExpression(result)
    }
    return result
}

interface Expression {
    fun evaluate(instructions: MutableMap<String, Expression>): Int
}

class SignalExpression(private val signalValue: Int): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = signalValue
}

private class PassExpression(val wire: String): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = evaluate(instructions, wire)
}

private class AndExpression(val lhs: Expression, val rhs: Expression): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = lhs.evaluate(instructions) and rhs.evaluate(instructions)
}

private class OrExpression(val lhs: Expression, val rhs: Expression): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = lhs.evaluate(instructions) or rhs.evaluate(instructions)
}

private class LShiftExpression(val lhs: Expression, val rhs: Expression): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = lhs.evaluate(instructions) shl rhs.evaluate(instructions)
}

private class RShiftExpression(val lhs: Expression, val rhs: Expression): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = lhs.evaluate(instructions) shr rhs.evaluate(instructions)
}

private class NotExpression(val expression: Expression): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = expression.evaluate(instructions).inv()
}
