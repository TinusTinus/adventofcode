package nl.mvdr.adventofcode.adventofcode2025.day09

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class Part2Test: FunctionSolverTest<Long>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("24", "example-day09.txt")
            )
        }
    }
}