package nl.mvdr.adventofcode.adventofcode2021.day13

/**
 * A two-dimensional axis.
 */
enum class Axis(val representation: String) {
    X("x"),
    Y("y")
}

/**
 * Parses the single-character [representation] of an axis.
 */
fun parseAxis(representation: String) = Axis.entries.first { it.representation == representation }