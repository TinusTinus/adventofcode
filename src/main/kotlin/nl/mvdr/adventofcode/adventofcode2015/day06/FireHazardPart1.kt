package nl.mvdr.adventofcode.adventofcode2015.day06

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int {
    var burningLights = setOf<Point>()
    for (instruction in lines.map(::parseInstruction)) {
        burningLights = instruction.apply(burningLights)
    }
    return burningLights.size
}

private fun parseInstruction(text: String): Instruction {
    val operator = Operator.entries.first { text.startsWith(it.representation) }

    val (lowerLeft, upperRight) = text.substring(operator.representation.length + 1)
        .split(" through ")
        .map(Point::parse)

    return Instruction(operator, PointRange(lowerLeft, upperRight))
}

private class Instruction(val operator: Operator, val pointRange: PointRange) {
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


fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day06-2015.txt")
    logger.info { result }
}
