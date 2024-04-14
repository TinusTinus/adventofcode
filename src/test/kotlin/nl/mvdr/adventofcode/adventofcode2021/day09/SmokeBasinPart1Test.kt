package nl.mvdr.adventofcode.adventofcode2021.day09

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SmokeBasinPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("15", "example-day09-2021.txt"),
            Arguments.of("452", "input-day09-2021.txt")
        )
    }
}