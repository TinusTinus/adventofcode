package nl.mvdr.adventofcode.adventofcode2021.day09

import nl.mvdr.adventofcode.point.Point

data class HeightMap(private val heights: Map<Point, Int>) {
    constructor(lines: Sequence<String>) : this(Point.parse2DMap(lines.toList()) { character -> character.toString().toInt() })

    fun sumLowPointRiskLevels() = findLowPoints().sumOf(this::riskValue)

    fun multiplyThreeLargestBasinSizes() = findLowPoints()
        .map(this::basinSize)
        .sorted()
        .takeLast(3)
        .reduce(Int::times)

    private fun findLowPoints(): List<Point> = heights.keys.filter(this::isLowPoint)

    private fun isLowPoint(point: Point) = point.neighbours()
        .map { heights[it] }
        .all { it == null || heights[point]!! < it }

    private fun riskValue(point: Point) = heights[point]!! + 1

    private fun basinSize(lowPoint: Point): Int {
        var latest = setOf(lowPoint)
        val basin = latest.toMutableSet()

        while (latest.isNotEmpty()) {
            latest = latest.asSequence()
                .flatMap(Point::neighbours)
                .filter { !basin.contains(it) }
                .filter { heights.containsKey(it) }
                .filter { heights[it]!! < 9 }
                .toSet()
            basin.addAll(latest)
        }

        return basin.size
    }
}
