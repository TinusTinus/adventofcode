package nl.mvdr.adventofcode.adventofcode2025.day07

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class Part2Test: FunctionSolverTest<Long>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("40", "example-day07.txt")
            )
        }
    }
}