package nl.mvdr.adventofcode.adventofcode2021.day06

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class LanternFishPart2Test: FunctionSolverTest<Long>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("26984457539", "example-day06-2021.txt"),
            Arguments.of("1675781200288", "input-day06-2021.txt")
        )
    }
}