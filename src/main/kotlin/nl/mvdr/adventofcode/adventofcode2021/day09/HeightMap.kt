package nl.mvdr.adventofcode.adventofcode2021.day09

import nl.mvdr.adventofcode.point.Point

data class HeightMap(private val heights: Map<Point, Int>) {
    constructor(lines: Sequence<String>) : this(Point.parse2DMap(lines.toList()) { character -> character.toString().toInt() })

    fun sumLowPointRiskLevels() = heights.entries
        .filter { isLowPoint(it.key) }
        .sumOf { it.value + 1 }

    /**
     * Checks whether the given [point] is a low point.
     * The given point must be an existing point in the height map.
     */
    private fun isLowPoint(point: Point) = point.neighbours()
        .map { heights[it] }
        .all { it == null || heights[point]!! < it }
}
