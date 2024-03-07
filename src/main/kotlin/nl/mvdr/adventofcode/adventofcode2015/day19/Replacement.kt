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
    fun apply(molecule: String): Set<String> = when {
        molecule == "" -> setOf()
        molecule.startsWith(from) -> setOf(to + molecule.drop(1)) union apply(molecule.drop(1)).map { molecule[0] + it }
        else -> apply(molecule.drop(1)).map { molecule[0] + it }.toSet()
    }
}

/**
 * Parses the textual representation of a replacement, such as "H => HO".
 */
fun parseReplacement(text: String): Replacement {
    val (from, to) = text.split(" => ")
    return Replacement(from, to)
}