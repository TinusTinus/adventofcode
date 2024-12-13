package nl.mvdr.adventofcode.adventofcode2019.day18

/**
 * Representation of a door, with an uppercase single-letter [name].
 */
data class Door(val name: Char) {
    override fun toString() = name.toString()
}
