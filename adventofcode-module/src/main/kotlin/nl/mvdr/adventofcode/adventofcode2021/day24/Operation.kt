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

        override fun evaluate(a: BitVecExpr, b: BitVecExpr, context: Context) = throw UnsupportedOperationException()
    },
    ADD("add") {
        override fun evaluate(a: BitVecExpr, b: BitVecExpr, context: Context) = context.mkBVAdd(a, b)!!
    },
    MUL("mul") {
        override fun evaluate(a: BitVecExpr, b: BitVecExpr, context: Context) = context.mkBVMul(a, b)!!
    },
    DIV("div") {
        override fun evaluate(a: BitVecExpr, b: BitVecExpr, context: Context) = context.mkBVSDiv(a, b)!!
    },
    MOD("mod") {
        override fun evaluate(a: BitVecExpr, b: BitVecExpr, context: Context) = context.mkBVSMod(a, b)!!
    },
    EQL("eql") {
        override fun evaluate(a: BitVecExpr, b: BitVecExpr, context: Context) = context.mkITE(context.mkEq(a, b), context.mkBV(1, 64), context.mkBV(0, 64))!!
    };

    open fun perform(state: State, a: Variable, b: Expression?, optimize: Optimize, context: Context): State {
        val newConst = context.mkBVConst(a.representation + "_" +  counter++, 64)

        val aValue = state.variables[a]!!
        val bValue = b!!.getValue(state, context)
        val expression = evaluate(aValue, bValue, context)

        optimize.Add(context.mkEq(newConst, expression))

        val newVariables = state.variables.toMutableMap()
        newVariables[a] = newConst
        return state.copy(variables = newVariables)
    }

    protected abstract fun evaluate(a: BitVecExpr, b: BitVecExpr, context: Context): Expr<BitVecSort>
}

/**
 * Parses the string [representation] of an operation.
 */
fun parseOperation(representation: String) = Operation.entries.first { it.representation == representation }