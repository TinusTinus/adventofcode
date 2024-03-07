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
    fun apply(molecule: String): List<String> = when {
        molecule == "" -> listOf()
        molecule.startsWith(from) -> (listOf(to + molecule.drop(from.length)) + sequenceOf(molecule).flatMap(::tailApply)).shuffled()
        else -> tailApply(molecule)
    }

    /**
     * Helper function which applies this replacement to the tail of the given [molecule].
     * That is, everything except the first character.
     */
    private fun tailApply(molecule: String) = apply(molecule.drop(1)).map { molecule[0] + it }

    /**
     * Reverses this replacement: to becomes from and vice versa.
     */
    fun reverse() = Replacement(to, from)
}

/**
 * Parses the textual representation of a replacement, such as "H => HO".
 */
fun parseReplacement(text: String): Replacement {
    val (from, to) = text.split(" => ")
    return Replacement(from, to)
}