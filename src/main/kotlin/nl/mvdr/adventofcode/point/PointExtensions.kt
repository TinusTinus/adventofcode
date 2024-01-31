package nl.mvdr.adventofcode.point

// Add some explicit operators to the Point class
operator fun Point.plus(other: Point): Point = this.add(other)
operator fun Point.minus(other: Point): Point = this.subtract(other)
operator fun Point.unaryMinus(): Point = this.negate()
operator fun Point.times(i: Int): Point = this.multiply(i)
operator fun Int.times(point: Point): Point = point.multiply(this)
