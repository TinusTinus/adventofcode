package nl.mvdr.adventofcode.adventofcode2015.day20

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class InfiniteElvesAndInfiniteHousesPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("884520", "input-day20-2015.txt")
        )
    }
}
