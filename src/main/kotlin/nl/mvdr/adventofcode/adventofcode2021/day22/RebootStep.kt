package nl.mvdr.adventofcode.adventofcode2021.day22

import nl.mvdr.adventofcode.point.Point3D

data class RebootStep(val operation: RebootStepOperation, val cuboid: Cuboid) {
    fun perform(turnedOnCubes: Set<Point3D>): Set<Point3D> = operation.perform(turnedOnCubes, cuboid)
}

/**
 * Parses the [text] representation of a reboot step.
 * For example: "on x=10..12,y=10..12,z=10..12".
 * The boolean parameter [limitToInitializationProcedureArea] indicates whether the cuboid should be limited
 * to the initialization procedure area: x=-50..50,y=-50..50,z=-50..50.
 */
fun parseRebootStep(text: String, limitToInitializationProcedureArea: Boolean): RebootStep {
    val (operationString, cuboidString) = text.split(" ")
    val operation = RebootStepOperation.entries.first { it.text == operationString }
    val cuboid = Cuboid(cuboidString, limitToInitializationProcedureArea)
    return RebootStep(operation, cuboid)
}
