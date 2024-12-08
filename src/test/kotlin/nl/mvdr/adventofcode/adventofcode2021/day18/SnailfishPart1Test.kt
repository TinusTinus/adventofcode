package nl.mvdr.adventofcode.adventofcode2021.day18

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SnailfishPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("4140", "example-day18-2021.txt")
        )
    }
}