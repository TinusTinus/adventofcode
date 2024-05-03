package nl.mvdr.adventofcode.adventofcode2021.day24

/**
 * The state of the unit, containing a value for all [variables], as well as any remaining [input] values.
 */
data class State(val input: List<Int>, val variables: Map<Variable, Int> = Variable.entries.associateWith { 0 })
