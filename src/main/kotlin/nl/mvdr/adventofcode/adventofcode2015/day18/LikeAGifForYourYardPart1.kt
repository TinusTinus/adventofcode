package nl.mvdr.adventofcode.adventofcode2015.day18

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>, steps: Int = 100): Int {
    val lights = Point.parse2DMap(lines.toList(), ::parseLight)
    for (i in 0 until steps) {
        // TODO take a step!
    }
    return lights.values.count { it == Light.ON }
}

private fun newState(point: Point, lights: Map<Point, Light>): Light = when (lights[point]!!) {
    Light.ON -> Light.ON // TODO
    Light.OFF -> Light.OFF // TODO
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day18-2015.txt")
    logger.info { result }
}
