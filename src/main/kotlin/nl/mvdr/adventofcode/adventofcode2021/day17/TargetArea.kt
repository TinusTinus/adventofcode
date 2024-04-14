package nl.mvdr.adventofcode.adventofcode2021.day17

import nl.mvdr.adventofcode.point.Point

data class TargetArea(private val x: IntRange, private val y: IntRange) {
// data class TargetArea(val minX: Int, val maxX: Int, val minY: Int, val maxY: Int) {
    fun contains(point: Point) = point.x in x && point.y in y
}

private const val PREFIX = "target area: "

/**
 * Parses the [text] representation of a target area.
 * For example: "target area: x=20..30, y=-10..-5"
 */
fun parseTargetArea(text: String): TargetArea {
    if (!text.startsWith(PREFIX)) {
        throw IllegalArgumentException("Text does not start with expected prefix '$PREFIX': '$text'")
    }
    val (x, y) = text.substring(PREFIX.length)
        .split(", ")
        .map(::parseRange)
    return TargetArea(x, y)
}

/**
 * Parses the [text] representation of an integer range.
 * For example: "x=20..30" or "y=-10..-5".
 * Note that this method only returns the range,
 * as represented by the right-hand side of the = sign.
 * It does not perform any validations on the left-hand side.
 */
private fun parseRange(text: String): IntRange {
    val (_, rhs) = text.split("=")
    val (min, max) = rhs.split("..")
        .map(String::toInt)
    return min .. max
}