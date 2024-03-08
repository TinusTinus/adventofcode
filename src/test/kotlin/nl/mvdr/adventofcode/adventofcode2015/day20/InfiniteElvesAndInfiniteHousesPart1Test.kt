package nl.mvdr.adventofcode.adventofcode2015.day20

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class InfiniteElvesAndInfiniteHousesPart1Test: FunctionSolverTest<Int>(::solvePart1) {

    @ParameterizedTest
    @MethodSource
    fun testPresentsDeliveredAt(expected: Int, house: Int) = assertEquals(expected, presentsDeliveredAt(house))

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
            Arguments.of(130, 9)
        )


        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("1", "example-day20-2015-0.txt"),
            Arguments.of("1", "example-day20-2015-1.txt"),
            Arguments.of("3", "example-day20-2015-2.txt"),
            Arguments.of("8", "example-day20-2015-3.txt"),
            Arguments.of("?", "input-day20-2015.txt")
        )
    }
}
