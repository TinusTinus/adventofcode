package nl.mvdr.adventofcode.adventofcode2015.day17

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ContainersTest {
    @Test
    fun testCountPart1() {
        val containerVolumes = listOf(20, 15, 10, 5, 5)
        val eggnog = 25

        val result = countWaysToFitInContainers(containerVolumes, eggnog)

        assertEquals(4, result)
    }

    @Test
    fun testCountPart2() {
        val containerVolumes = listOf(20, 15, 10, 5, 5)
        val eggnog = 25
        val containersToUse = 2

        val result = countWaysToFitInContainers(containerVolumes, eggnog, containersToUse)

        assertEquals(3, result)
    }
}