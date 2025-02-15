package nl.mvdr.adventofcode.adventofcode2021.day13

import nl.mvdr.adventofcode.point.Point

/**
 * A two-dimensional axis.
 */
enum class Axis(val representation: String) {
    X("x") {
        override fun getValue(point: Point) = point.x
        override fun updateValue(point: Point, newValue: Int) = Point(newValue, point.y)
    },
    Y("y") {
        override fun getValue(point: Point) = point.y
        override fun updateValue(point: Point, newValue: Int) = Point(point.x, newValue)
    };

    abstract fun getValue(point: Point): Int

    /**
     * Creates a copy of the given [point], with the value in this axis updated to the given [newValue].
     */
    abstract fun updateValue(point: Point, newValue: Int): Point
}

/**
 * Parses the single-character [representation] of an axis.
 */
fun parseAxis(representation: String) = Axis.entries.first { it.representation == representation }