package nl.mvdr.adventofcode.adventofcode2021.day08

/**
 * A decoder is capable of decoding an output value.
 */
data class Decoder(private val mapping: Map<SignalPattern, Int>) {

    /**
     * Creates a decoder, based on the given [uniqueSignalPatterns].
     * The given list must contain ten patterns: one unique pattern for each digit.
     * The resulting decoder maps each signal pattern to the corresponding digit.
     */
    constructor(uniqueSignalPatterns: List<SignalPattern>) : this(createMapping(uniqueSignalPatterns))

    /**
     * Decodes the given output [value].
     */
    fun decode(value: List<SignalPattern>) = value.map { mapping[it]!! }.joinToString("").toInt()
}

/**
 * Creates a mapping from unique signal patterns to the digit each of them represents.
 */
private fun createMapping(uniqueSignalPatterns: List<SignalPattern>): Map<SignalPattern, Int> =
    findPatterns(uniqueSignalPatterns).entries.associate { (digit, pattern) -> pattern to digit }

/**
 * Finds, for each digit, the corresponding unique signal pattern.
 */
private fun findPatterns(uniqueSignalPatterns: List<SignalPattern>): Map<Int, SignalPattern> {
    val result = mutableMapOf<Int, SignalPattern>()

    result[1] = uniqueSignalPatterns.first(SignalPattern::representsOne)
    result[4] = uniqueSignalPatterns.first(SignalPattern::representsFour)
    result[7] = uniqueSignalPatterns.first(SignalPattern::representsSeven)
    result[8] = uniqueSignalPatterns.first(SignalPattern::representsEight)

    result[9] = uniqueSignalPatterns.first { it.representsNine(result[4]!!) }
    result[0] = uniqueSignalPatterns.first { it.representsZero(result[1]!!, result[9]!!) }
    result[6] = uniqueSignalPatterns.first { it.representsSix(result[0]!!, result[9]!!) }

    result[3] = uniqueSignalPatterns.first { it.representsThree(result[1]!!) }
    result[5] = uniqueSignalPatterns.first { it.representsFive(result[3]!!, result[6]!!) }
    result[2] = uniqueSignalPatterns.first { it.representsTwo(result[3]!!, result[5]!!) }

    return result
}
