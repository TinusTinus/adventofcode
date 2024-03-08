package nl.mvdr.adventofcode.adventofcode2015.day20

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class InfiniteElvesAndInfiniteHousesPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("?", "input-day20-2015.txt")
            //  693000 is too low
            //  831600 is the minimum  (= part 1 answer)
            // 1164240 is too high
            // 3272727 is too high
        )
    }
}
