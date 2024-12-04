package nl.mvdr.adventofcode.adventofcode2015.day20

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class InfiniteElvesAndInfiniteHousesPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("1", "example-day20-2015-0.txt"),
            Arguments.of("1", "example-day20-2015-1.txt"),
            Arguments.of("3", "example-day20-2015-2.txt"),
            Arguments.of("8", "example-day20-2015-3.txt")
        )
    }
}
