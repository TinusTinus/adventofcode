package nl.mvdr.adventofcode.adventofcode2021.day24

import com.microsoft.z3.*

private var counter: Int = 0

enum class Operation(val representation: String) {
    INP("inp") {
        override fun perform(state: State, a: Variable, b: Expression?, optimize: Optimize, context: Context): State {
            val newInput = state.input.drop(1)
            val newVariables = state.variables.toMutableMap()
            newVariables[a] = state.input.first()
            return State(newInput, newVariables)
        }

        override fun evaluate(a: IntExpr, b: IntExpr, context: Context) = throw UnsupportedOperationException()
    },
    ADD("add") {
        override fun evaluate(a: IntExpr, b: IntExpr, context: Context) = context.mkAdd(a, b)!!
    },
    MUL("mul") {
        override fun evaluate(a: IntExpr, b: IntExpr, context: Context) = context.mkMul(a, b)!!
    },
    DIV("div") {
        override fun evaluate(a: IntExpr, b: IntExpr, context: Context) = context.mkDiv(a, b)!!
    },
    MOD("mod") {
        override fun evaluate(a: IntExpr, b: IntExpr, context: Context) = context.mkMod(a, b)!!
    },
    EQL("eql") {
        override fun evaluate(a: IntExpr, b: IntExpr, context: Context) = context.mkITE(context.mkEq(a, b), context.mkInt(1), context.mkInt(0))!!
    };

    open fun perform(state: State, a: Variable, b: Expression?, optimize: Optimize, context: Context): State {
        val newConst = context.mkIntConst(a.representation + "_" +  counter++)

        val aValue = state.variables[a]!!
        val bValue = b!!.getValue(state, context)
        val expression = evaluate(aValue, bValue, context)

        optimize.Add(context.mkEq(newConst, expression))

        val newVariables = state.variables.toMutableMap()
        newVariables[a] = newConst
        return state.copy(variables = newVariables)
    }

    protected abstract fun evaluate(a: IntExpr, b: IntExpr, context: Context): Expr<IntSort>
}

/**
 * Parses the string [representation] of an operation.
 */
fun parseOperation(representation: String) = Operation.entries.first { it.representation == representation }