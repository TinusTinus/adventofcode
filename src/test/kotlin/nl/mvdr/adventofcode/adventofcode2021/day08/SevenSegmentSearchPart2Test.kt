package nl.mvdr.adventofcode.adventofcode2021.day08

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SevenSegmentSearchPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("5353", "example-day08-2021-0.txt"),
            Arguments.of("61229", "example-day08-2021-1.txt")
        )
    }
}