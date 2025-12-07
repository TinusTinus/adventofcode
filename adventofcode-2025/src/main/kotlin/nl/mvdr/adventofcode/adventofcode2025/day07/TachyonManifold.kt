package nl.mvdr.adventofcode.adventofcode2025.day07

import nl.mvdr.adventofcode.point.Point

data class TachyonManifold(val start: Point, val splitters: Set<Point>) {
    private val maxY = Point.maxY(splitters)
    private val timelineCache = mutableMapOf<Point, Long>()

    fun countSplits(beamY: Int = start.y, beamXs: Set<Int> = setOf(start.x), splitsSoFar: Int = 0): Int = when(beamY) {
        maxY + 1 -> splitsSoFar
        else -> countSplits(
            beamY + 1,
            beamXs.flatMap { beamX ->
                if (splitters.contains(Point(beamX, beamY))) {
                    setOf(beamX - 1, beamX + 1)
                } else {
                    setOf(beamX)
                }
            }.toSet(),
            splitsSoFar + beamXs.count { beamX -> splitters.contains(Point(beamX, beamY)) }
        )
    }

    fun countTimelines(beam: Point = start): Long =
        timelineCache.getOrPut(beam) {
            if (beam.y == maxY + 1) {
                // The beam will never encounter another splitter
                1
            } else if (splitters.contains(beam)) {
                // The beam splits here
                countTimelines(Point(beam.x - 1, beam.y + 1)) +
                        countTimelines(Point(beam.x + 1, beam.y + 1))
            } else {
                countTimelines(Point(beam.x, beam.y + 1))
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
