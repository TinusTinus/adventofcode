package nl.mvdr.adventofcode.adventofcode2021.day10

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SyntaxScoringPart1Test: FunctionSolverTest<Long>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("26397", "example-day10-2021.txt"),
            Arguments.of("358737", "input-day10-2021.txt")
        )
    }
}