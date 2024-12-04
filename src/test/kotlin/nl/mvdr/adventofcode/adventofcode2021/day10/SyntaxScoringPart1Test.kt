package nl.mvdr.adventofcode.adventofcode2021.day10

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SyntaxScoringPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("26397", "example-day10-2021.txt")
        )
    }
}