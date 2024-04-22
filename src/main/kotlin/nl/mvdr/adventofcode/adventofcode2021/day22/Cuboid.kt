package nl.mvdr.adventofcode.adventofcode2021.day22

import nl.mvdr.adventofcode.point.Axis3D
import nl.mvdr.adventofcode.point.Point3D

data class Cuboid(val x: IntRange, val y: IntRange, val z: IntRange) {
    constructor(ranges: Map<Axis3D, IntRange>) : this(
        ranges[Axis3D.X] ?: IntRange.EMPTY,
        ranges[Axis3D.Y] ?: IntRange.EMPTY,
        ranges[Axis3D.Z] ?: IntRange.EMPTY)

    /**
     * Parses the [text] representation of a cuboid.
     * For example: "x=11..13,y=11..13,z=11..13"
     */
    constructor(text: String) : this(parseCoordinateRanges(text))

    val cubes get() = x.flatMap { xValue -> y.flatMap { yValue -> z.map { zValue -> Point3D(xValue, yValue, zValue) } } }.toSet()

    fun getRange(axis: Axis3D) = when(axis) {
        Axis3D.X -> x
        Axis3D.Y -> y
        Axis3D.Z -> z
    }
}

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
