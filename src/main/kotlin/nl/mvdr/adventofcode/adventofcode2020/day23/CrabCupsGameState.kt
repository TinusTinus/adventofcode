package nl.mvdr.adventofcode.adventofcode2020.day23

class CrabCupsGameState {
    constructor(lines: Sequence<String>) {
        val cupNumbers = lines.first().map { it.toString().toInt() }

        current = cupNumbers[0]

        cups = mutableMapOf()
        for (i in cupNumbers.indices) {
            cups[cupNumbers[i]] = cupNumbers[(i + 1) % cupNumbers.size]
        }
    }

    /** Label of the first cup in the list. */
    private var current: Int

    /**
     * Cups, represented as a map.
     * Keys in the map are cup labels.
     * Values are the label of the next cup when going clockwise.
     */
    private val cups: MutableMap<Int, Int>

    fun perform(turns: Int) {
        for (i in 0 until turns) {
            move()
        }
    }


    private fun move() {
        // Pick up cups
        val pickedUpCups = mutableSetOf(cups[current]!!)
        while (pickedUpCups.size < 3) {
            pickedUpCups.add(cups[pickedUpCups.last()]!!)
        }
        cups[current] = cups[pickedUpCups.last()]!!

        // Select destination
        var destination = current
        while (destination == current || pickedUpCups.contains(destination)) {
            destination = when (destination) {
                1 -> cups.size
                else -> destination - 1
            }
        }

        // Place the picked up cups
        cups[pickedUpCups.last()] = cups[destination]!!
        cups[destination] = pickedUpCups.first()

        // Select a new current cup
        current = cups[current]!!
    }

    fun cupsClockwiseFrom1(): String {
        val result = StringBuilder()
        var cup = cups[1]!!

        while (cup != 1) {
            result.append(cup)
            cup = cups[cup]!!
        }

        return result.toString()
    }

    override fun toString(): String {
        val result = StringBuilder("cups: ($current)")
        var cup = cups[current]!!
        while (cup != current) {
            result.append(" $current ")
            cup = cups[cup]!!
        }
        return result.toString()
    }
}