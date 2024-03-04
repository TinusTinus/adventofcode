package nl.mvdr.adventofcode.adventofcode2015.day14

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ReindeerTest {

    @Test
    fun testComet() {
        val comet = Reindeer("Comet", 14, 10, 127)

        val distance = comet.distanceAfter(1000)

        assertEquals(1120, distance)
    }

    @Test
    fun testDancer() {
        val dancer = Reindeer("Dancer", 16, 11, 162)

        val distance = dancer.distanceAfter(1000)

        assertEquals(1056, distance)
    }

    @Test
    fun test1Second() {
        val comet = Reindeer("Comet", 14, 10, 127)

        val distance = comet.distanceAfter(1)

        assertEquals(14, distance)
    }

    @Test
    fun test0Seconds() {
        val comet = Reindeer("Comet", 14, 10, 127)

        val distance = comet.distanceAfter(0)

        assertEquals(0, distance)
    }

}