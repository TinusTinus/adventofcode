import nl.mvdr.adventofcode.point.Point

import nl.mvdr.adventofcode.point.plus
import nl.mvdr.adventofcode.point.times
import nl.mvdr.adventofcode.point.unaryMinus

fun main() {
    println("Hello world, this is a Kotlin function")

    val point0 = Point(0, 1)
    val point1 = Point(2, 3)

    val sum = point0 + point1
    println("$point0 + $point1 = $sum")

    val negative = -point1
    println("-($point1) = $negative")

    val lessThan = point0 < point1
    println("$point0 < $point1 : $lessThan")

    val product = point0.multiply(3)
    println("$point0 * 3 = $product")

    val product1 = 3 * point0
    println("3 * $point0 = $product1")
}