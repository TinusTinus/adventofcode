package nl.mvdr.adventofcode.adventofcode2021.day08

/**
 * A segment in a seven-segment display.
 * Can be turned on or off to display part of a digit.
 */
enum class Segment(val character: Char) {
    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g')
}

/**
 * Parses the single [character] representation of a segment.
 */
fun parseSegment(character: Char) = Segment.entries.first { it.character == character }