package nl.mvdr.adventofcode.adventofcode2021.day08

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SevenSegmentSearchPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("0", "example-day08-2021-0.txt"),
            Arguments.of("26", "example-day08-2021-1.txt")
        )
    }
}