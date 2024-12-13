package nl.mvdr.adventofcode.adventofcode2021.day21

/**
 * Representation of a 100-sided deterministic die.
 * Note that this class is mutable.
 */
class DeterministicDie {
    private var nextRoll: Int = 1

    var rolls: Int = 0
        private set

    fun roll(): Int {
        val result = nextRoll
        nextRoll = nextRoll % 100 + 1
        rolls++
        return result
    }
}