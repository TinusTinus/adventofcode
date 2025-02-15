package nl.mvdr.adventofcode.adventofcode2020.day23

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class CrabCupsPart2Test: FunctionSolverTest<Any>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("149245887792", "example-day23-2020.txt")
        )
    }
}