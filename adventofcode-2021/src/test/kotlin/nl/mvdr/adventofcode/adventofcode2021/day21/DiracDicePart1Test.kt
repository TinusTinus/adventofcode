package nl.mvdr.adventofcode.adventofcode2021.day21

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class DiracDicePart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("739785", "example-day21-2021.txt")
        )
    }
}