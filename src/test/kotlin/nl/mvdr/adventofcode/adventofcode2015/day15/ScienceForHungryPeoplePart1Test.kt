package nl.mvdr.adventofcode.adventofcode2015.day15

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ScienceForHungryPeoplePart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("62842880", "example-day15-2015.txt"),
            Arguments.of("?", "input-day15-2015.txt")
        )
    }
}
