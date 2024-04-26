package nl.mvdr.adventofcode.adventofcode2021.day23

enum class AmphipodType(val representation: Char, private val energyPerStep: Int) {
    AMBER('A', 1),
    BRONZE('B', 10),
    COPPER('C', 100),
    DESERT('D', 1000);

    /**
     * Computes how much energy it costs to move from [start] to [target].
     */
    fun move(start: Space, target: Space) = start.location.manhattanDistance(target.location) * energyPerStep
}

fun parseAmphipodType(representation: Char) = AmphipodType.entries.first { it.representation == representation }
