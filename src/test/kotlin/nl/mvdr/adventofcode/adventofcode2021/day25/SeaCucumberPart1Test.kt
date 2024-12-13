package nl.mvdr.adventofcode.adventofcode2021.day25

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SeaCucumberPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("58", "example-day25-2021-0.txt"),
            Arguments.of("8", "example-day25-2021-50.txt"),
            Arguments.of("3", "example-day25-2021-55.txt"),
            Arguments.of("2", "example-day25-2021-56.txt"),
            Arguments.of("1", "example-day25-2021-57.txt")
        )
    }
}