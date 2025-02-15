package nl.mvdr.adventofcode.adventofcode2021.day23

/**
 * Type of amphipod.
 * The [x] value is the x coordinate of the type's target side room.
 */
enum class AmphipodType(val representation: Char, val energyPerStep: Int, val x: Int) {
    AMBER('A', 1, 3),
    BRONZE('B', 10, 5),
    COPPER('C', 100, 7),
    DESERT('D', 1000, 9);
}
