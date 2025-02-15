package nl.mvdr.adventofcode.adventofcode2015.day18

/**
 * Represents the state of a light.
 */
enum class Light(val representation: Char) {
    ON('#'),
    OFF('.')
}

/**
 * Parses a single-character [representation] of a light.
 */
fun parseLight(representation: Char) = Light.entries.first { it.representation == representation }