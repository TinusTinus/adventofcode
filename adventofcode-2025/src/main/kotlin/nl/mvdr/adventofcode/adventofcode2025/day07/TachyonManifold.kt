package nl.mvdr.adventofcode.adventofcode2025.day07

import nl.mvdr.adventofcode.point.Point

data class TachyonManifold(val start: Point, val splitters: Set<Point>) {
    private val maxY = Point.maxY(splitters)
    private val timelineCache = mutableMapOf<Point, Int>()

    fun countSplits() = countSplitsStartingFrom(start.y, setOf(start.x), 0)

    private fun countSplitsStartingFrom(y: Int, beamXs: Set<Int>, splitsSoFar: Int): Int = when(y) {
        maxY + 1 -> splitsSoFar
        else -> countSplitsStartingFrom(
            y + 1,
            beamXs.flatMap { x ->
                if (splitters.contains(Point(x, y))) {
                    setOf(x - 1, x + 1)
                } else {
                    setOf(x)
                }
            }.toSet(),
            splitsSoFar + beamXs.count { x -> splitters.contains(Point(x, y)) }
        )
    }

    fun countTimelines() = countTimelinesStartingFrom(start)

    private fun countTimelinesStartingFrom(point: Point): Int =
        timelineCache.getOrPut(point) {
            if (point.y == maxY + 1) {
                1
            } else if (splitters.contains(point)) {
                countTimelinesStartingFrom(
                    Point(
                        point.x - 1,
                        point.y + 1
                    )
                ) + countTimelinesStartingFrom(Point(point.x + 1, point.y + 1))
            } else {
                countTimelinesStartingFrom(Point(point.x, point.y + 1))
            }
        }
}

fun parseManifold(lines: List<String>): TachyonManifold {
    var start: Point? = null
    val splitters = mutableSetOf<Point>()

    Point.parse2DMap(lines) {point, character ->
        when (character) {
            'S' -> start = point
            '^' -> splitters.add(point)
            '.' -> {}
            else -> throw IllegalArgumentException("Unexpected input character: $character")
        }
    }

    return TachyonManifold(start!!, splitters)
}
