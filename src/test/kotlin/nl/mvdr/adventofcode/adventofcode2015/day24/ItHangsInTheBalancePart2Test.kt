package nl.mvdr.adventofcode.adventofcode2015.day24

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ItHangsInTheBalancePart2Test: FunctionSolverTest<Long>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("44", "example-day24-2015.txt")
        )
    }
}