package nl.mvdr.adventofcode.adventofcode2015.day06

import nl.mvdr.adventofcode.point.Point

class Instruction(text: String) {
    private val operator: Operator = Operator.entries.first { text.startsWith(it.representation) }
    private val pointRange: PointRange

    init {
        val (lowerLeft, upperRight) = text.substring(operator.representation.length + 1)
            .split(" through ")
            .map(Point::parse)
        this.pointRange = PointRange(lowerLeft, upperRight)
    }

    fun apply(burningLights: Set<Point>): Set<Point> = operator.apply(burningLights, pointRange)
}

private enum class Operator(val representation: String) {
    TURN_ON("turn on") {
        override fun apply(burningLights: Set<Point>, lights: Set<Point>): Set<Point> = burningLights union lights
    },
    TURN_OFF("turn off") {
        override fun apply(burningLights: Set<Point>, lights: Set<Point>): Set<Point> = burningLights subtract lights
    },
    TOGGLE("toggle") {
        override fun apply(burningLights: Set<Point>, lights: Set<Point>): Set<Point> = (burningLights union lights) subtract (burningLights intersect lights)
    };

    fun apply(burningLights: Set<Point>, lights: PointRange): Set<Point> = apply(burningLights, lights.points())

    abstract fun apply(burningLights: Set<Point>, lights: Set<Point>): Set<Point>
}

private class PointRange(val lowerLeft: Point, val upperRight: Point) {
    fun points(): Set<Point> {
        return (lowerLeft.x .. upperRight.x).flatMap { x ->
            (lowerLeft.y .. upperRight.y).map { y -> Point(x, y) }
        }.toSet()
    }
}