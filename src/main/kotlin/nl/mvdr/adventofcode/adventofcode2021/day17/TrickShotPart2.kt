package nl.mvdr.adventofcode.adventofcode2021.day17

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val targetArea = parseTargetArea(lines.first())

    val xVelocityRange = 1 .. targetArea.x.max() + 1
    val yVelocityRange = targetArea.y.min() - 1 .. 1000 // Note that this max y velocity was pretty much pulled from thin air

    return xVelocityRange.flatMap { xVelocity -> yVelocityRange.map { yVelocity -> Point(xVelocity, yVelocity) } }
        .map { Probe(Point.ORIGIN, it) }
        .count { it.willReachTargetArea(targetArea) }
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day17-2021.txt")
    logger.info { result }
}
