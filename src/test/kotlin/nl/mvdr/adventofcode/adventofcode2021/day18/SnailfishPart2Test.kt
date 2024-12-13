package nl.mvdr.adventofcode.adventofcode2021.day18

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SnailfishPart2Test: FunctionSolverTest<Any>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("3993", "example-day18-2021.txt")
        )
    }
}