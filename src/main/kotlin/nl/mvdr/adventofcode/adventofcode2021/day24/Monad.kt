package nl.mvdr.adventofcode.adventofcode2021.day24

import com.microsoft.z3.Context
import com.microsoft.z3.Status

data class Monad(private val program: Program) {

    constructor(lines: Sequence<String>) : this(Program(lines))

    fun findMaxModelNumber(): String = Context().use(::findMaxModelNumber)

    private fun findMaxModelNumber(context: Context): String {
        val optimize = context.mkOptimize()

        val modelNumber = context.mkIntConst("modelNumber")
        optimize.MkMaximize(modelNumber)

        val digits = (0 until 14).map { context.mkIntConst("d_$it") }
        digits.forEach { optimize.Add(context.mkLe(context.mkInt(1), it)) }
        digits.forEach { optimize.Add(context.mkLe(it, context.mkInt(9))) }
        val sum = context.mkAdd(*(digits.mapIndexed { index, value -> context.mkMul(value, context.mkPower(context.mkInt(10), context.mkInt(13 - index))) }.toTypedArray()))
        optimize.Add(context.mkEq(modelNumber, sum))

        // TODO add program constraints

        check(optimize.Check() == Status.SATISFIABLE) { "Failed to solve: $optimize" }
        return optimize.model.evaluate(modelNumber, false).toString()
    }
}
