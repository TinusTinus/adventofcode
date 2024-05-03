package nl.mvdr.adventofcode.adventofcode2021.day24

import com.microsoft.z3.*

data class Monad(private val program: Program) {

    constructor(lines: Sequence<String>) : this(Program(lines))

    fun findModelNumber(findMinimum: Boolean = false): String = Context().use { findModelNumber(it, findMinimum) }

    private fun findModelNumber(context: Context, findMinimum: Boolean): String {
        val optimize = context.mkOptimize()

        val modelNumber = context.mkBVConst("modelNumber",  64)
        if (findMinimum) {
            optimize.MkMinimize(modelNumber)
        } else {
            optimize.MkMaximize(modelNumber)
        }

        val digits = (0 until 14).map { context.mkBVConst("d_$it", 64) }
        digits.forEach { optimize.Add(context.mkBVSLE(context.mkBV(1, 64), it)) }
        digits.forEach { optimize.Add(context.mkBVSLE(it, context.mkBV(9, 64))) }
        val terms = digits.mapIndexed { index, value -> context.mkBVMul(value, context.mkInt2BV(64, context.mkPower(context.mkInt(10), context.mkInt(13 - index)))) }
        val sum = terms.reduce { lhs, rhs -> context.mkBVAdd(lhs, rhs) }
        optimize.Add(context.mkEq(modelNumber, sum))

        val endState = program.execute(digits, optimize, context)

        optimize.Add(context.mkEq(endState.variables[Variable.Z], context.mkBV(0, 64)))

        check(optimize.Check() == Status.SATISFIABLE) { "Failed to solve: $optimize" }
        return optimize.model.evaluate(modelNumber, false).toString()
    }
}
