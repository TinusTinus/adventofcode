package nl.mvdr.adventofcode.adventofcode2021.day10

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SyntaxScoringPart2Test: FunctionSolverTest<Long>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("288957", "example-day10-2021.txt"),
            Arguments.of("4329504793", "input-day10-2021.txt")
        )
    }
}