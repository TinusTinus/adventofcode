package nl.mvdr.adventofcode.adventofcode2021.day08

/**
 * A signal pattern, which represents a single digit.
 * The set of [segments] indicates which segments have been turned on.
 * The segments not in this set are turned off.
 */
data class SignalPattern(private val segments: Set<Segment>) {

    constructor(stringRepresentation: String) : this(stringRepresentation.map(::parseSegment).toSet())

    val size = segments.size

    /**
     * Determines whether the given [other] pattern's segments are all included in this pattern.
     * For example, the signal pattern for seven contains both segments of the signal pattern for one.
     */
    fun containsAllSegments(other: SignalPattern) = segments.containsAll(other.segments)
}

/**
 * Parses the [stringRepresentation] of a signal pattern.
 * For example: "acedgfb".
 */
fun parseSignalPattern(stringRepresentation: String) = SignalPattern(stringRepresentation.map(::parseSegment).toSet())