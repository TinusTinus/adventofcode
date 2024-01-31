import nl.mvdr.adventofcode.point.Point

fun main() {
    println("Hello world, this is a Kotlin function")

    // Add some Kotlin operators to the Point class
    operator fun Point.plus(other: Point) = this.add(other)
    operator fun Point.minus(other: Point) = this.subtract(other)
    operator fun Point.unaryMinus() = this.negate()
    operator fun Point.times(i: Int) = this.multiply(i)
    operator fun Int.times(point: Point) = point.multiply(this)

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