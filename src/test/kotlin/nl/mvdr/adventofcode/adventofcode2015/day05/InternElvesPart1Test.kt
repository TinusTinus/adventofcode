package nl.mvdr.adventofcode.adventofcode2015.day05

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class InternElvesPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("1", "example-day05-2015-0.txt"),
                Arguments.of("1", "example-day05-2015-1.txt"),
                Arguments.of("2", "example-day05-2015-2.txt")
            )
        }
    }
}