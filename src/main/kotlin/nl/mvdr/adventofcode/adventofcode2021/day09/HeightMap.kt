package nl.mvdr.adventofcode.adventofcode2021.day09

import nl.mvdr.adventofcode.point.Point

data class HeightMap(private val heights: Map<Point, Int>) {
    constructor(lines: Sequence<String>) : this(Point.parse2DMap(lines.toList()) { character -> character.toString().toInt() })

    fun sumLowPointRiskLevels() = findLowPoints().sumOf(this::riskValue)

    private fun findLowPoints(): List<Point> = heights.keys.filter(this::isLowPoint)

    private fun isLowPoint(point: Point) = point.neighbours()
        .map { heights[it] }
        .all { it == null || heights[point]!! < it }

    private fun riskValue(point: Point) = heights[point]!! + 1
}
