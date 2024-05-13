package nl.mvdr.adventofcode.adventofcode2020.day23

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger{}

class CrabCupsGameState(lines: List<String>, numberOfCups: Int = lines.first().length) {

    /** Label of the first cup in the list. */
    private var current: Int

    /**
     * Cups, represented as a map.
     * Keys in the map are cup labels.
     * Values are the label of the next cup when going clockwise.
     */
    private val cups: MutableMap<Int, Int>

    init {
        val cupNumbers = lines.first().map { it.toString().toInt() }

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
        logger.debug { this }

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

    fun cupsClockwiseFrom1(): String {
        val result = StringBuilder()
        var cup = cups[1]!!

        while (cup != 1) {
            result.append(cup)
            cup = cups[cup]!!
        }

        return result.toString()
    }

    fun productOfCupsClockwiseFrom1(): Long {
        val lhs = cups[1]!!
        val rhs = cups[lhs]!!
        return lhs.toLong() * rhs.toLong()
    }

    override fun toString(): String {
        val result = StringBuilder("cups: ($current)")
        var cup = cups[current]!!
        while (cup != current) {
            result.append(" $cup ")
            cup = cups[cup]!!
        }
        return result.toString()
    }

}