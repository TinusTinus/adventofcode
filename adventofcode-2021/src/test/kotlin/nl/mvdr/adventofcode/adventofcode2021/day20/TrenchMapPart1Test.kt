package nl.mvdr.adventofcode.adventofcode2021.day20

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class TrenchMapPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("35", "example-day20-2021.txt")
        )
    }
}