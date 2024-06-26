package nl.mvdr.adventofcode.adventofcode2021.day18

import kotlin.math.ceil

/**
 * Representation of a regular number within a snailfish number.
 * If the snailfish number is regular, the [value] must be single-digit,
 * that is, it is at least 0 and less than 10.
 */
data class RegularNumber(val value: Int): SnailfishElement {
    /**
     * The magnitude of a regular number is just that number.
     */
    override fun magnitude() = value

    override fun explode(depth: Int) = null

    override fun addToLeftmostRegularNumber(toAdd: Int): SnailfishElement = add(toAdd)

    override fun addToRightmostRegularNumber(toAdd: Int): SnailfishElement = add(toAdd)

    private fun add(toAdd: Int) = RegularNumber(value + toAdd)

    override fun split() = when {
        10 <= value -> SnailfishNumber(RegularNumber(value / 2), RegularNumber(ceil(value.toDouble() / 2.0).toInt()))
        else -> null
    }

    override fun toString() = value.toString()
}

/**
 * Parses a prefix of the given [text] as a regular number element of a snailfish number.
 * Returns the regular number and the remaining part of the given text.
 * Note that this function assumes that the snailfish number represented by the entire text is regular.
 */
fun parsePrefixAsRegularNumber(text: String): Pair<RegularNumber, String> {
    val value = text.substring(0 until 1).toInt()
    return Pair(RegularNumber(value), text.substring(1))
}
