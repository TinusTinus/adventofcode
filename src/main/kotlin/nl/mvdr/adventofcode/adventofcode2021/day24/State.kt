package nl.mvdr.adventofcode.adventofcode2021.day24

import com.microsoft.z3.Context
import com.microsoft.z3.IntExpr

/**
 * The state of the unit, containing a value for all [variables], as well as any remaining [input] values.
 */
data class State(val input: List<IntExpr>, val variables: Map<Variable, IntExpr>) {
    constructor(input: List<IntExpr>, context: Context) : this(input, Variable.entries.associateWith { context.mkInt(0) })
}
