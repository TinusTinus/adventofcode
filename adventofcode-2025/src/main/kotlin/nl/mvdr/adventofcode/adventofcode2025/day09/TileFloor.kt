package nl.mvdr.adventofcode.adventofcode2025.day09

import nl.mvdr.adventofcode.point.Point

data class TileFloor(val redTiles: List<Point>) {
    fun findLargestRectangleArea() =
        redTiles.flatMap { firstCorner -> redTiles.map { secondCorner -> Rectangle(firstCorner, secondCorner) } }
            .maxOf(Rectangle::area)

    fun findLargestRedOrGreenRectangleArea() = 3L // todo implement. Maybe take inspiration from 2023 day 10?
}

fun parseTileFloor(lines: Sequence<String>) = TileFloor(lines.map(Point::parse).toList())
