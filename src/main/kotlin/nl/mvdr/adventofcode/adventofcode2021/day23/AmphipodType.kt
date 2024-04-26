package nl.mvdr.adventofcode.adventofcode2021.day23

enum class AmphipodType(val representation: Char, val energyPerStep: Int) {
    AMBER('A', 1),
    BRONZE('B', 10),
    COPPER('C', 100),
    DESERT('D', 1000);
}
