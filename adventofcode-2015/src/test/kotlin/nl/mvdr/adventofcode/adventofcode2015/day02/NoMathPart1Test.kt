package nl.mvdr.adventofcode.adventofcode2015.day02

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class NoMathPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("58", "example-day02-2015-0.txt"),
                Arguments.of("43", "example-day02-2015-1.txt")
            )
        }
    }
}