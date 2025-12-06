package nl.mvdr.adventofcode.adventofcode2025.day06

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class Part1Test: FunctionSolverTest<Long>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("4277556", "example-day06.txt")
            )
        }
    }
}