package nl.mvdr.adventofcode.adventofcode2015.day02

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class NoMathPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("34", "example-day02-2015-0.txt"),
                Arguments.of("14", "example-day02-2015-1.txt")
            )
        }
    }
}