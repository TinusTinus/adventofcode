package nl.mvdr.adventofcode.adventofcode2021.day18

/**
 * Every snailfish number is a pair - an ordered list of two elements.
 * Each element of the pair can be either a regular number or another pair.
 */
data class SnailfishNumber(private val left: SnailfishElement, private val right: SnailfishElement): SnailfishElement {
    infix operator fun plus(other: SnailfishElement) = SnailfishNumber(this, other).reduce()

    /**
     * Reduces this snailfish number.
     */
    private fun reduce(): SnailfishNumber = when (val reduced = reduceAction()) {
        null -> this
        else -> reduced.reduce()
    }

    /**
     * Performs a single reduction action: either explode or split, if possible.
     * Returns null if no more actions are possible.
     */
    private fun reduceAction() = explode() ?: split()

    fun explode(): SnailfishNumber? = when (val exploded = explode(0)) {
        null -> null
        else -> exploded.element as SnailfishNumber
    }

    override fun explode(depth: Int) = when (depth) {
        4 -> {
            val leftValue = (left as RegularNumber).value
            val rightValue = (right as RegularNumber).value
            ExplosionResult(leftValue, RegularNumber(0), rightValue)
        }
        else -> explodeLeft(depth) ?: explodeRight(depth)
    }

    private fun explodeLeft(depth: Int) = when (val exploded = left.explode(depth + 1)) {
        null -> null
        else -> {
            val newRight = when (exploded.toAddRight) {
                null -> right
                else -> right.addToLeftmostRegularNumber(exploded.toAddRight)
            }
            ExplosionResult(exploded.toAddLeft, SnailfishNumber(exploded.element, newRight), null)
        }
    }

    private fun explodeRight(depth: Int) = when (val exploded = right.explode(depth + 1)) {
        null -> null
        else -> {
            val newLeft = when (exploded.toAddLeft) {
                null -> left
                else -> left.addToRightmostRegularNumber(exploded.toAddLeft)
            }
            ExplosionResult(null, SnailfishNumber(newLeft, exploded.element), exploded.toAddRight)
        }
    }

    override fun addToLeftmostRegularNumber(toAdd: Int) = SnailfishNumber(left.addToLeftmostRegularNumber(toAdd), right)

    override fun addToRightmostRegularNumber(toAdd: Int) = SnailfishNumber(left, right.addToRightmostRegularNumber(toAdd))

    override fun split(): SnailfishNumber? {
        return when (val leftSplit = left.split()) {
            null -> {
                when (val rightSplit = right.split()) {
                    null -> null
                    else -> SnailfishNumber(left, rightSplit)
                }
            }
            else -> SnailfishNumber(leftSplit, right)
        }
    }

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
        throw IllegalArgumentException("Snailfish number must be separated by ','. Unable to parse: '$text'")
    }

    val (right, remaining1) = parsePrefixAsElement(remaining0.substring(1))
    if (remaining1.first() != ']') {
        throw IllegalArgumentException("Snailfish number must end with ']'. Unable to parse: '$text'")
    }

    val number = SnailfishNumber(left, right)
    return Pair(number, remaining1.substring(1))
}

