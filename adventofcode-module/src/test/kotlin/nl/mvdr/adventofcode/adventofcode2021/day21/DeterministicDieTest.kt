package nl.mvdr.adventofcode.adventofcode2021.day21

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DeterministicDieTest {

    @Test
    fun testRoll() {
        val die = DeterministicDie()

        assertEquals(1, die.roll())
        assertEquals(2, die.roll())
        assertEquals(3, die.roll())
        assertEquals(4, die.roll())
    }

    @Test
    fun testRollover() {
        val die = DeterministicDie()
        for (i in 0 until 99) {
            assertEquals(i + 1, die.roll())
        }
        assertEquals(100, die.roll())
        assertEquals(1, die.roll())
    }
}