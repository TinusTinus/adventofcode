package nl.mvdr.adventofcode.adventofcode2021.day14

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ExtendedPolymerizationPart2Test: FunctionSolverTest<Long>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("2188189693529", "example-day14-2021.txt")
        )
    }
}