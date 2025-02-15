package nl.mvdr.adventofcode.adventofcode2015.day15

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ScienceForHungryPeoplePart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("57600000", "example-day15-2015.txt")
        )
    }
}
