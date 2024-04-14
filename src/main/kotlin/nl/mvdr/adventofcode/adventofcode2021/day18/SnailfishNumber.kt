package nl.mvdr.adventofcode.adventofcode2021.day18

/**
 * Every snailfish number is a pair - an ordered list of two elements.
 * Each element of the pair can be either a regular number or another pair.
 */
data class SnailfishNumber(private val left: SnailfishElement, private val right: SnailfishElement): SnailfishElement {
    infix operator fun plus(other: SnailfishElement) = SnailfishNumber(this, other).reduce()

    private fun reduce(): SnailfishNumber = this // TODO implement!

    /**
     * The magnitude of a pair is 3 times the magnitude of its left element plus 2 times the magnitude of its right element.
     */
    override fun magnitude() = 3 * left.magnitude() + 2 * right.magnitude()

    override fun toString() = "[$left,$right]"
}

/**
 * Parses a prefix of the given [text] as a regular snailfish number.
 */
fun parseSnailfishNumber(text: String): SnailfishNumber {
    val (result, remaining) = parsePrefixAsSnailfishNumber(text)
    if (remaining.isNotEmpty()) {
        throw IllegalArgumentException("Unable to parse as snailfish number: '$text'. There are trailing characters: '$remaining'")
    }
    return result
}

/**
 * Parses a prefix of the given [text] as a snailfish number.
 * Returns the snailfish number and the remaining part of the given text.
 */
fun parsePrefixAsSnailfishNumber(text: String): Pair<SnailfishNumber, String> {
    if (text.first() != '[') {
        throw IllegalArgumentException("Snailfish number must start with '['. Unable to parse: '$text'")
    }

    val (left, remaining0) = parsePrefixAsElement(text.substring(1))
    if (remaining0.first() != ',') {
        throw IllegalArgumentException("Snailfih number must be separated by ','. Unable to parse: '$text'")
    }

    val (right, remaining1) = parsePrefixAsElement(remaining0.substring(1))
    if (remaining1.first() != ']') {
        throw IllegalArgumentException("Snailfish number must end with ']'. Unable to parse: '$text'")
    }

    val number = SnailfishNumber(left, right)
    return Pair(number, remaining1.substring(1))
}

