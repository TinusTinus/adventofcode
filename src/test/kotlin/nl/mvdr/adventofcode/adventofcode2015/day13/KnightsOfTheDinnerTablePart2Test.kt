package nl.mvdr.adventofcode.adventofcode2015.day13

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class KnightsOfTheDinnerTablePart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("725", "input-day13-2015.txt")
        )
    }
}
