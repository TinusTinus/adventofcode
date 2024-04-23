package nl.mvdr.adventofcode.adventofcode2021.day22

/**
 * Solves the puzzle.
 * [lines]: input file, containing a textual representation of the reboot steps.
 * [limitToInitializationProcedureArea]: whether to limit the procedure
 * to the initialization procedure area: x=-50..50,y=-50..50,z=-50..50.
 */
fun solve(lines: Sequence<String>, limitToInitializationProcedureArea: Boolean): Long {
    val rebootSteps = lines.map { parseRebootStep(it, limitToInitializationProcedureArea) }.toList()

    var turnedOn = emptySet<Cuboid>()
    for (rebootStep in rebootSteps) {
        turnedOn = rebootStep.perform(turnedOn)
    }
    return turnedOn.sumOf(Cuboid::countCubes)
}
