package nl.mvdr.adventofcode.adventofcode2021.day13

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class TransparentOrigamiPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("17", "example-day13-2021.txt")
        )
    }
}