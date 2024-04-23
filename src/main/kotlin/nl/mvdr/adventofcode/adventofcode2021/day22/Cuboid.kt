package nl.mvdr.adventofcode.adventofcode2021.day22

import nl.mvdr.adventofcode.point.Axis3D
import nl.mvdr.adventofcode.point.Point3D
import kotlin.math.max
import kotlin.math.min

data class Cuboid(val x: IntRange, val y: IntRange, val z: IntRange) {
    constructor(ranges: Map<Axis3D, IntRange>) : this(
        ranges[Axis3D.X] ?: IntRange.EMPTY,
        ranges[Axis3D.Y] ?: IntRange.EMPTY,
        ranges[Axis3D.Z] ?: IntRange.EMPTY)

    /**
     * Parses the [text] representation of a cuboid.
     * For example: "x=11..13,y=11..13,z=11..13"
     * The boolean parameter [limitToInitializationProcedureArea] indicates whether the cuboid should be limited
     * to the initialization procedure area: x=-50..50,y=-50..50,z=-50..50.
     */
    constructor(text: String, limitToInitializationProcedureArea: Boolean) : this(parseCoordinateRanges(text, limitToInitializationProcedureArea))

    private val cubes get() = x.flatMap { xValue -> y.flatMap { yValue -> z.map { zValue -> Point3D(xValue, yValue, zValue) } } }.toSet()

    fun countCubes() = x.count().toLong() * y.count().toLong() * z.count().toLong()

    fun isEmpty() = x.isEmpty() || y.isEmpty() || z.isEmpty()

    /**
     * Returns a set of cuboids, containing all points within this cuboid, except the points in the given other cuboid.
     */
    fun minus(other: Cuboid): Set<Cuboid> {
        val overlap = overlap(other)

        val result: Set<Cuboid>
        if (overlap.isEmpty()) {
            result = setOf(this)
        } else {
            // TODO split up into larger cuboids
            result = (cubes - overlap.cubes)
                .map { Cuboid(it.x .. it.x, it.y .. it.y, it.z .. it.z) }
                .toSet()
        }

        return result
    }

    /**
     * Determines the cuboid consisting of all points contained within both this and the given [other] cuboid.
     * The resulting cuboid may be empty.
     */
    private fun overlap(other: Cuboid): Cuboid = Cuboid(overlap(this.x, other.x), overlap(this.y, other.y), overlap(this.z, other.z))

    fun getRange(axis: Axis3D) = when(axis) {
        Axis3D.X -> x
        Axis3D.Y -> y
        Axis3D.Z -> z
    }
}

private fun parseCoordinateRanges(text: String, limitToInitializationProcedureArea: Boolean) =
    text.split(",").associate { parseCoordinateRange(it, limitToInitializationProcedureArea) }

/**
 * Parses the given [text] as a range for a specific coordinate axis.
 * For example: "x=3..4".
 */
private fun parseCoordinateRange(text: String, limitToInitializationProcedureArea: Boolean): Pair<Axis3D, IntRange> {
    val (axisString, rangeString) = text.split("=")

    val axis = Axis3D.parse(axisString)

    var range = parseRange(rangeString)
    if (limitToInitializationProcedureArea) {
        range = limitToInitializationProcedureArea(range)
    }

    return Pair(axis, range)
}

/**
 * Parses the given [text] as an integer range.
 * For example: "3..4".
 */
private fun parseRange(text: String): IntRange {
    val (min, max) = text.split("..")
    return min.toInt() .. max.toInt()
}

/**
 * Limits the given [range] to the initialization procedure area: -50..50.
 * Note that the resulting range may be empty.
 */
private fun limitToInitializationProcedureArea(range: IntRange) = max(range.first, -50) .. min(range.last, 50)

/**
 * Determines the overlap between the given ranges.
 * Note that the overlap can be empty.
 */
private fun overlap(range0: IntRange, range1: IntRange) = max(range0.first, range1.first) .. min(range0.last, range1.last)
