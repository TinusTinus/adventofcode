package nl.mvdr.adventofcode.adventofcode2021.day22

fun solve(lines: Sequence<String>, limitToInitializationProcedureArea: Boolean): Long {
    val rebootSteps = lines.map { parseRebootStep(it, limitToInitializationProcedureArea) }.toList()

    // Note that the cuboids in the following steps must not overlap:
    // there must not be two or more cuboids containing the same point.
    var turnedOn = emptySet<Cuboid>()

    for (rebootStep in rebootSteps) {
        turnedOn = rebootStep.perform(turnedOn)
    }

    return turnedOn.sumOf(Cuboid::countCubes)
}
