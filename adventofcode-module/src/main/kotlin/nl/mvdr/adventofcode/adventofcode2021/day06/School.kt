package nl.mvdr.adventofcode.adventofcode2021.day06

private const val MAX_TIMER = 8

/**
 * A school of lanternfish.
 *
 * The given list [fish] keeps track, per timer value, how many fish there are with that particular timer value.
 * For example, if fish[3] equals 2, that means there are two fish whose timer has the value 3.
 */
data class School(private val fish: List<Long>) {
    /**
     * Constructor which parses the [lines] from the puzzle input file.
     */
    constructor(lines: Sequence<String>) : this(parseFish(lines))

    /**
     * Simulates a single day.
     */
    private fun simulateDay(): School {
        // Reduce all nonzero timer values
        val newFish = (0 until MAX_TIMER).map { fish[it + 1] }.toMutableList()
        // Spawn baby fish
        newFish.add(fish[0])
        // Update the timer values for the fish who just procreated
        newFish[6] += fish[0]
        return School(newFish)
    }

    /**
     * Simulates the given number of [days].
     */
    fun simulateDays(days: Int): School = when (days) {
        0 -> this
        else -> simulateDay().simulateDays(days - 1)
    }

    /**
     * Returns the number of fish in this school.
     */
    fun countFish() = fish.sum()
}

private fun parseFish(lines: Sequence<String>): List<Long> {
    val fish = MutableList(MAX_TIMER + 1) { 0L }
    lines.first()
        .split(",")
        .map(String::toInt)
        .forEach { fish[it]++ }
    return fish
}
