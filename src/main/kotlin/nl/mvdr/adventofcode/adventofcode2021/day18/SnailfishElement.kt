package nl.mvdr.adventofcode.adventofcode2021.day18

/**
 * Common superinterface for possible elements of a snailfish number.
 */
interface SnailfishElement {
    fun magnitude(): Int

    /**
     * Attempts to perform a single explode action, as part of a reduction.
     * The [depth] indicates the number of pairs in which this element is nested.
     * Returns null if the explode action is not applicable to this element.
     */
    fun explode(depth: Int): ExplosionResult?

    /**
     * Attempts to perform a single split action, as part of a reduction.
     * If successful, this function returns the updated element.
     * Otherwise, it returns null.
     */
    fun split(): SnailfishNumber?
}

/**
 * Parses a prefix of the given [text] as an element of a regular snailfish number.
 * Returns the element, followed by the remaining part of the given text.
 */
fun parsePrefixAsElement(text: String) = when {
    text.startsWith("[") -> parsePrefixAsSnailfishNumber(text)
    text.first().isDigit() -> parsePrefixAsRegularNumber(text)
    else -> throw IllegalArgumentException("Unable to parse any prefix as a snailfish element: $text")
}