package nl.mvdr.adventofcode.adventofcode2015.day14

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ReindeerTest {

    private val comet = Reindeer("Comet", 14, 10, 127)
    private val dancer = Reindeer("Dancer", 16, 11, 162)

    @Test
    fun testComet() = assertEquals(1120, comet.distanceAfter(1000))

    @Test
    fun testDancer() = assertEquals(1056, dancer.distanceAfter(1000))

    @Test
    fun test0Seconds() = assertEquals(0, comet.distanceAfter(0))

    @Test
    fun test1Second() = assertEquals(14, comet.distanceAfter(1))

    @Test
    fun testRaceForDistance() = assertEquals(1120, raceForDistance(listOf(comet, dancer), 1000))

    @Test
    fun testRaceForPoints() = assertEquals(689, raceForPoints(listOf(comet, dancer), 1000))
}