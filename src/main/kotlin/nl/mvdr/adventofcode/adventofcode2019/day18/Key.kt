package nl.mvdr.adventofcode.adventofcode2019.day18

/**
 * Representation of a key, with a lowercase single-letter [name].
 * Corresponds to the [Door] with the same name.
 */
data class Key(private val name: Char) {
    override fun toString() = name.toString()
}

