package nl.mvdr.adventofcode.adventofcode2015.day03

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SphericalHousesPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("2", "example-day03-2015-0.txt"),
                Arguments.of("4", "example-day03-2015-1.txt"),
                Arguments.of("2", "example-day03-2015-2.txt")
            )
        }
    }
}