package nl.mvdr.adventofcode.adventofcode2021.day24

import com.microsoft.z3.BitVecExpr
import com.microsoft.z3.Context

/**
 * The state of the unit, containing a value for all [variables], as well as any remaining [input] values.
 */
data class State(val input: List<BitVecExpr>, val variables: Map<Variable, BitVecExpr>) {
    constructor(input: List<BitVecExpr>, context: Context) : this(input, Variable.entries.associateWith { context.mkBV(0, 64) })
}
