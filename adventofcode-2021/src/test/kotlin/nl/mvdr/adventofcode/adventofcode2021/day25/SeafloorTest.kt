package nl.mvdr.adventofcode.adventofcode2021.day25

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SeafloorTest {
    @Test
    fun testStep() {
        val start = parseSeafloor(listOf("...>>>>>..."))

        val next = start.next

        assertEquals(parseSeafloor(listOf("...>>>>.>..")), next)
    }
}