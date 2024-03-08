package nl.mvdr.adventofcode.adventofcode2015.day20

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ElvesAndHousesTest {
    @ParameterizedTest
    @MethodSource
    fun testPresentsDeliveredAt(expected: Int, house: Int) =
        Assertions.assertEquals(expected, presentsDeliveredAt(house, 10))

    companion object {
        @JvmStatic
        fun testPresentsDeliveredAt(): List<Arguments> = listOf(
            Arguments.of(10, 1),
            Arguments.of(30, 2),
            Arguments.of(40, 3),
            Arguments.of(70, 4),
            Arguments.of(60, 5),
            Arguments.of(120, 6),
            Arguments.of(80, 7),
            Arguments.of(150, 8),
            Arguments.of(130, 9),
            Arguments.of(180, 10),
            Arguments.of(420, 20)
        )
    }
}