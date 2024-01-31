package nl.mvdr.adventofcode.adventofcode2015

import nl.mvdr.adventofcode.point.Point
import nl.mvdr.adventofcode.point.plus

fun main() {
    val point0 = Point(0, 1)
    val point1 = Point(2, 3)

    val sum = point0 + point1
    println("$point0 + $point1 = $sum")
}