package nl.mvdr.adventofcode.adventofcode2021.day12

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class PassagePathingPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("10", "example-day12-2021-0.txt"),
            Arguments.of("19", "example-day12-2021-1.txt"),
            Arguments.of("226", "example-day12-2021-2.txt"),
            Arguments.of("4411", "input-day12-2021.txt")
        )
    }
}