package nl.mvdr.adventofcode.adventofcode2021.day22

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.point.Point3D

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>): Int {
    val rebootSteps = lines.map(::parseRebootStep)
        .map(RebootStep::limitToInitializationProcedureArea)
        .toList()

    var turnedOnCubes = emptySet<Point3D>()

    for (rebootStep in rebootSteps) {
        turnedOnCubes = rebootStep.perform(turnedOnCubes)
    }

    return turnedOnCubes.size
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day22-2021.txt")
    logger.info { result }
}
