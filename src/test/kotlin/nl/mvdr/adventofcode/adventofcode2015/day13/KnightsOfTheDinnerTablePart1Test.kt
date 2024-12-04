package nl.mvdr.adventofcode.adventofcode2015.day13

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class KnightsOfTheDinnerTablePart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("330", "example-day13-2015.txt")
        )
    }
}
