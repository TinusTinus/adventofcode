package nl.mvdr.adventofcode.adventofcode2021.day14

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ExtendedPolymerizationPart1Test: FunctionSolverTest<Long>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("1588", "example-day14-2021.txt")
        )
    }
}