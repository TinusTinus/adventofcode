package nl.mvdr.adventofcode.adventofcode2021.day08

/**
 * A signal pattern, which represents a single digit.
 * The set of [segments] indicates which segments have been turned on.
 */
data class SignalPattern(private val segments: Set<Segment>) {

    /**
     * Creates a signal pattern based on its [stringRepresentation].
     * For example: "acedgfb".
     */
    constructor(stringRepresentation: String) : this(stringRepresentation.map(::parseSegment).toSet())

    /**
     * Checks whether this pattern is relatively easy to identify.
     * That is, whether this pattern has a number of segments which uniquely corresponds to a digit.
     */
    fun isEasy() = representsOne() || representsFour() || representsSeven() || representsEight()

    fun representsZero(one: SignalPattern, nine: SignalPattern) =
        segments.size == 6 && this != nine && segments.containsAll(one.segments)

    fun representsOne() = segments.size == 2

    fun representsTwo(three: SignalPattern, five: SignalPattern) =
        segments.size == 5 && this != three && this != five

    fun representsThree(one: SignalPattern) = segments.size == 5 && segments.containsAll(one.segments)

    fun representsFour() = segments.size == 4

    fun representsFive(three: SignalPattern, six: SignalPattern) =
        segments.size == 5 && this != three && six.segments.containsAll(segments)

    fun representsSix(zero: SignalPattern, nine: SignalPattern) =
        segments.size == 6 && this != zero && this != nine

    fun representsSeven() = segments.size == 3

    fun representsEight() = segments.size == 7

    fun representsNine(four: SignalPattern) = segments.size == 6 && segments.containsAll(four.segments)
}
