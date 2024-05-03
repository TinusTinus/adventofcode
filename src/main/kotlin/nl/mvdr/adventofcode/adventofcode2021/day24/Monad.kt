package nl.mvdr.adventofcode.adventofcode2021.day24

import com.microsoft.z3.Context
import com.microsoft.z3.Status

data class Monad(private val program: Program) {

    constructor(lines: Sequence<String>) : this(Program(lines))

    fun findMaxModelNumber(): String = Context().use(::findMaxModelNumber)

    private fun findMaxModelNumber(context: Context): String {
        val optimize = context.mkOptimize()

        val digits = (0 until 14).map { context.mkIntConst("d_$it") }
        digits.forEach { context.mkLe(context.mkInt(1), it) }
        digits.forEach { context.mkLe(it, context.mkInt(9)) }

        val modelNumber = context.mkIntConst("modelNumber")
        val sum = context.mkAdd(*(digits.mapIndexed { index, value -> context.mkMul(value, context.mkPower(context.mkInt(10), context.mkInt(14 - index))) }.toTypedArray()))
        optimize.Add(context.mkEq(modelNumber, sum))

        // TODO add constraints based on the program!

        check(optimize.Check() == Status.SATISFIABLE) { "Failed to solve: $optimize" }
        val result = optimize.model.evaluate(modelNumber, false)
        return optimize.MkMaximize(modelNumber).toString()
    }
}
