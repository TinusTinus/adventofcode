package nl.mvdr.adventofcode.adventofcode2021.day18

/**
 * Common superinterface for possible elements of a snailfish number.
 */
interface SnailfishElement {
    fun magnitude(): Int

    /**
     * Attempts to perform a single split step, as part of a reduction.
     * If successful, this function returns the updated element.
     * Otherwise, it returns null.
     */
    fun split(): SnailfishElement?
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