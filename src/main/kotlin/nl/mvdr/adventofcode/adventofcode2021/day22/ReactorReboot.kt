package nl.mvdr.adventofcode.adventofcode2021.day22

import nl.mvdr.adventofcode.point.Point3D

fun solve(lines: Sequence<String>, limitToInitializationProcedureArea: Boolean): Int {
    val rebootSteps = lines.map { parseRebootStep(it, limitToInitializationProcedureArea) }.toList()

    var turnedOnCubes = emptySet<Point3D>()

    for (rebootStep in rebootSteps) {
        turnedOnCubes = rebootStep.perform(turnedOnCubes)
    }

    return turnedOnCubes.size
}
