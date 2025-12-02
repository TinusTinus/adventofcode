package nl.mvdr.adventofcode.adventofcode2025.day03

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class Part1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("?", "example-day03.txt")
            )
        }
    }
}