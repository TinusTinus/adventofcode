package nl.mvdr.adventofcode.adventofcode2021.day22

import nl.mvdr.adventofcode.point.Axis3D
import nl.mvdr.adventofcode.point.Point3D

data class Cuboid(val ranges: Map<Axis3D, IntRange>) {
    val x get() = ranges[Axis3D.X] ?: IntRange.EMPTY
    val y get() = ranges[Axis3D.Y] ?: IntRange.EMPTY
    val z get() = ranges[Axis3D.Z] ?: IntRange.EMPTY
    val cubes get() = x.flatMap { xValue -> y.flatMap { yValue -> z.map { zValue -> Point3D(xValue, yValue, zValue) } } }.toSet()
}

/**
 * Parses the [text] representation of a cuboid.
 * For example: "x=11..13,y=11..13,z=11..13"
 */
fun parseCuboid(text: String) = Cuboid(parseCoordinateRanges(text))

private fun parseCoordinateRanges(text: String) = text.split(",").associate(::parseCoordinateRange)

/**
 * Parses the given [text] as a range for a specific coordinate axis.
 * For example: "x=3..4".
 */
private fun parseCoordinateRange(text: String): Pair<Axis3D, IntRange> {
    val (axis, range) = text.split("=")
    return Pair(Axis3D.parse(axis), parseRange(range))
}

/**
 * Parses the given [text] as an integer range.
 * For example: "3..4".
 */
private fun parseRange(text: String): IntRange {
    val (min, max) = text.split("..")
    return min.toInt() .. max.toInt()
}
