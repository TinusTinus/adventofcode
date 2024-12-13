package nl.mvdr.adventofcode.adventofcode2020.day23

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger{}

/**
 * Representation of the game state in a game of Crab Cups.
 *
 * @param text textual representation of the initial labeling; for example: "389125467"
 * @param numberOfCups total number of cups; can be used to add additional cups after the ones in the initial labeling
 */
class CrabCupsGameState(text: String, numberOfCups: Int = text.length) {

    /** Label of the first cup in the list. */
    private var current: Int

    /**
     * Cups, represented as a map.
     * Keys in the map are cup labels.
     * Values are the label of the next cup when going clockwise.
     * For example, the starting cup labeling "389125467" is represented as follows:
     * - 3 -> 8
     * - 8 -> 9
     * - 9 -> 1
     * - 1 -> 2
     * - 2 -> 5
     * - 5 -> 4
     * - 4 -> 6
     * - 6 -> 7
     * - 7 -> 3
     */
    private val cups: MutableMap<Int, Int>

    init {
        val cupNumbers = text.map { it.toString().toInt() }

        current = cupNumbers[0]

        cups = mutableMapOf()
        var latest: Int? = null
        for (i in 0 until cupNumbers.size - 1) {
            cups[cupNumbers[i]] = cupNumbers[i + 1]
            latest = cupNumbers[i + 1]
        }

        for (i in cupNumbers.size + 1 .. numberOfCups) {
            cups[latest!!] = i
            latest = i
        }

        cups[latest!!] = cupNumbers[0]
    }

    fun perform(turns: Int) {
        for (i in 0 until turns) {
            move()
        }
    }

    private fun move() {
        logger.debug { "cups: $this" }

        // Pick up cups
        val pickedUpCups = mutableSetOf(cups[current]!!)
        while (pickedUpCups.size < 3) {
            pickedUpCups.add(cups[pickedUpCups.last()]!!)
        }
        cups[current] = cups[pickedUpCups.last()]!!
        logger.debug { "pick up: $pickedUpCups" }

        // Select destination
        var destination = current
        while (destination == current || pickedUpCups.contains(destination)) {
            destination = when (destination) {
                1 -> cups.size
                else -> destination - 1
            }
        }
        logger.debug { "destination: $destination" }

        // Place the picked up cups
        cups[pickedUpCups.last()] = cups[destination]!!
        cups[destination] = pickedUpCups.first()

        // Select a new current cup
        current = cups[current]!!
    }

    /**
     * Returns a string representation of the labels of the cups after cup 1.
     */
    fun cupsClockwiseFrom1(): String {
        val result = StringBuilder()
        var cup = cups[1]!!

        while (cup != 1) {
            result.append(cup)
            cup = cups[cup]!!
        }

        return result.toString()
    }

    /**
     * Returns the product of the labels of the two cups immediately clockwise of cup 1.
     */
    fun productOfCupsClockwiseFrom1(): Long {
        val lhs = cups[1]!!
        val rhs = cups[lhs]!!
        return lhs.toLong() * rhs.toLong()
    }

    override fun toString(): String {
        val result = StringBuilder("($current)")
        var cup = cups[current]!!
        while (cup != current) {
            result.append(" $cup ")
            cup = cups[cup]!!
        }
        return result.toString()
    }

}