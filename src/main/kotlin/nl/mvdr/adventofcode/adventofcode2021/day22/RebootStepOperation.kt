package nl.mvdr.adventofcode.adventofcode2021.day22

/**
 * An operation in a reboot step.
 */
enum class RebootStepOperation(val text: String) {
    /**
     * Turn on all cubes within the specified cuboid.
     */
    TURN_ON("on"),

    /**
     * Turn off all cubes within the specified cuboid.
     */
    TURN_OFF("off")
}