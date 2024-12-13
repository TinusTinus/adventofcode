package nl.mvdr.adventofcode.adventofcode2015.day25

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class LetItSnowPart1Test: FunctionSolverTest<Long>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("27995004", "example-day25-2015.txt")
        )
    }
}