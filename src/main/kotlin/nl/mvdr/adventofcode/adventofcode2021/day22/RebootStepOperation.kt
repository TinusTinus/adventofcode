package nl.mvdr.adventofcode.adventofcode2021.day22

import nl.mvdr.adventofcode.point.Point3D

/**
 * An operation in a reboot step.
 */
enum class RebootStepOperation(val text: String) {
    /**
     * Turn on all cubes within the specified cuboid.
     */
    TURN_ON("on") {
        override fun perform(turnedOnCubes: Set<Point3D>, cuboid: Cuboid) = turnedOnCubes + cuboid.cubes
    },

    /**
     * Turn off all cubes within the specified cuboid.
     */
    TURN_OFF("off") {
        override fun perform(turnedOnCubes: Set<Point3D>, cuboid: Cuboid) = turnedOnCubes - cuboid.cubes
    };

    abstract fun perform(turnedOnCubes: Set<Point3D>, cuboid: Cuboid): Set<Point3D>
}