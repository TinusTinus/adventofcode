package nl.mvdr.adventofcode.adventofcode2021.day22

import nl.mvdr.adventofcode.point.Axis3D
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

    fun countCubes() = x.count().toLong() * y.count().toLong() * z.count().toLong()

    /**
     * Returns a set of cuboids, containing all points within this cuboid, except the points in the given other cuboid.
     */
    fun minus(other: Cuboid): Set<Cuboid> {
        val overlap = overlap(other)
        return when {
            overlap.isEmpty() -> setOf(this)
            else -> split(x, overlap.x)
                .flatMap { xRange -> split(y, overlap.y)
                    .flatMap { yRange -> split(z, overlap.z)
                        .map { zRange -> Cuboid(xRange, yRange, zRange) }
                    }
                }
                .filter { it != overlap }
                .toSet()
        }
    }

    /**
     * Determines the cuboid consisting of all points contained within both this and the given [other] cuboid.
     * The resulting cuboid may be empty.
     */
    private fun overlap(other: Cuboid): Cuboid = Cuboid(overlap(this.x, other.x), overlap(this.y, other.y), overlap(this.z, other.z))

    private fun isEmpty() = x.isEmpty() || y.isEmpty() || z.isEmpty()
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

/**
 * Splits the given [range] into three subranges: before the [overlap], the [overlap] itself and after the [overlap].
 * Any empty ranges are filtered from the result.
 */
private fun split(range: IntRange, overlap: IntRange) = listOf(
    range.first until overlap.first,
    overlap,
    overlap.last + 1 .. range.last
).filter { !it.isEmpty() }
