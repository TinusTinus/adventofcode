package nl.mvdr.adventofcode.adventofcode2015.day01

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class NotQuiteLispPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("1", "example-day01-2015-9.txt"),
                Arguments.of("5", "example-day01-2015-10.txt")
            )
        }
    }
}