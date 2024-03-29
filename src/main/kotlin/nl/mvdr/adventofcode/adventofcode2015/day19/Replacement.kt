package nl.mvdr.adventofcode.adventofcode2015.day19

/**
 * Representation of a single replacement.
 */
data class Replacement(val from: String, val to: String) {
    override fun toString(): String = "$from => $to"

    /**
     * Applies this replacement to the given [molecule].
     * Returns all molecules which can be obtained by performing the replacement.
     */
    fun apply(molecule: String): Sequence<String> = when {
        molecule == "" -> sequenceOf()
        molecule.startsWith(from) -> sequenceOf(to + molecule.drop(from.length)) + sequenceOf(molecule).flatMap(::tailApply)
        else -> tailApply(molecule)
    }

    /**
     * Helper function which applies this replacement to the tail of the given [molecule].
     * That is, everything except the first character.
     */
    private fun tailApply(molecule: String) = apply(molecule.drop(1)).map { molecule[0] + it }
}

/**
 * Parses the textual representation of a replacement, such as "H => HO".
 */
fun parseReplacement(text: String): Replacement {
    val (from, to) = text.split(" => ")
    return Replacement(from, to)
}