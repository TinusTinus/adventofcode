package nl.mvdr.adventofcode.adventofcode2025.day09

import nl.mvdr.adventofcode.point.Point
import kotlin.math.abs

data class Rectangle(private val firstCorner: Point, private val secondCorner: Point) {
    private val width = abs(firstCorner.x - secondCorner.x) + 1
    private val height = abs(firstCorner.y - secondCorner.y) + 1
    val area = width.toLong() * height.toLong()
}
