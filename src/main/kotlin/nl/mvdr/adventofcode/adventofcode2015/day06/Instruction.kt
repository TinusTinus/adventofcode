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

    fun applyPart1(burningLights: Set<Point>): Set<Point> = operator.applyPart1(burningLights, pointRange)

    fun applyPart2(brightness: MutableMap<Point, Int>) = operator.applyPart2(brightness, pointRange)
}

private enum class Operator(val representation: String, val brightnessDelta: Int) {
    TURN_ON("turn on", 1) {
        override fun applyPart1(burningLights: Set<Point>, lights: Set<Point>): Set<Point> = burningLights union lights
    },
    TURN_OFF("turn off", -1) {
        override fun applyPart1(burningLights: Set<Point>, lights: Set<Point>): Set<Point> = burningLights subtract lights
    },
    TOGGLE("toggle", 2) {
        override fun applyPart1(burningLights: Set<Point>, lights: Set<Point>): Set<Point> = (burningLights union lights) subtract (burningLights intersect lights)
    };

    fun applyPart1(burningLights: Set<Point>, lights: PointRange): Set<Point> = applyPart1(burningLights, lights.points())

    abstract fun applyPart1(burningLights: Set<Point>, lights: Set<Point>): Set<Point>

    fun applyPart2(brightness: MutableMap<Point, Int>, lights: PointRange) {
        for (point in lights.points()) {
            val currentValue = brightness.getOrDefault(point, 0)
            val newValue = maxOf(currentValue + brightnessDelta, 0)
            brightness.put(point, newValue)
        }
    }
}

private class PointRange(val lowerLeft: Point, val upperRight: Point) {
    fun points(): Set<Point> {
        return (lowerLeft.x .. upperRight.x).flatMap { x ->
            (lowerLeft.y .. upperRight.y).map { y -> Point(x, y) }
        }.toSet()
    }
}