package nl.mvdr.adventofcode.adventofcode2025.day04

import nl.mvdr.adventofcode.point.CharacterConsumer
import nl.mvdr.adventofcode.point.Point


fun parseInput(lines: Sequence<String>): Set<Point> {
    val result = mutableSetOf<Point>();
    Point.parse2DMap(lines.toList(), CharacterConsumer { point, character -> if (character == '@') { result.add(point) } })
    return result
}