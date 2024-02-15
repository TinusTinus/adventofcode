package nl.mvdr.adventofcode.adventofcode2015.day07

fun parseInstruction(text: String, instructions: MutableMap<String, Expression>) {
    val (expressionString, wire) = text.split(" -> ")
    val expression = when {
        expressionString.all(Char::isDigit) -> SignalExpression(expressionString.toInt())
        expressionString.startsWith("NOT") -> NotExpression(expressionString.substring(4))
        expressionString.contains("OR") -> {
            val (lhs, rhs) = expressionString.split(" OR ")
            OrExpression(lhs, rhs)
        }
        expressionString.contains("AND") -> {
            val (lhs, rhs) = expressionString.split(" AND ")
            AndExpression(lhs, rhs)
        }
        expressionString.contains("LSHIFT") -> {
            val (lhs, rhs) = expressionString.split(" LSHIFT ")
            LShiftExpression(lhs, rhs.toInt())
        }
        expressionString.contains("RSHIFT") -> {
            val (lhs, rhs) = expressionString.split(" RSHIFT ")
            RShiftExpression(lhs, rhs.toInt())
        }
        else -> PassExpression(expressionString)
    }
    instructions[wire] = expression
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

private class SignalExpression(val signalValue: Int): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = signalValue
}

private class AndExpression(val lhs: String, val rhs: String): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = evaluate(instructions, lhs) and evaluate(instructions, rhs)
}

private class OrExpression(val lhs: String, val rhs: String): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = evaluate(instructions, lhs) or evaluate(instructions, rhs)
}

private class LShiftExpression(val lhs: String, val rhs: Int): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = evaluate(instructions, lhs) shl rhs
}

private class RShiftExpression(val lhs: String, val rhs: Int): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = evaluate(instructions, lhs) shr rhs
}

private class NotExpression(val wire: String): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = evaluate(instructions, wire).inv()
}

private class PassExpression(val wire: String): Expression {
    override fun evaluate(instructions: MutableMap<String, Expression>): Int = evaluate(instructions, wire)
}