package nl.mvdr.adventofcode.adventofcode2021.day18

/**
 * Result of an intermediate explosion operation.
 * The [element] is the new element replacing the old one.
 * The [toAddLeft] value is the value to be added to the first regular number to the left of this element, if available.
 * The [toAddRight] value is the value to be added to the first regular number to the right of this element, if available.
 */
data class ExplosionResult(val toAddLeft: Int?, val element: SnailfishElement, val toAddRight: Int?)
