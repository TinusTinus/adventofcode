package nl.mvdr.adventofcode.adventofcode2015.day01

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class NotQuiteLispPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("0", "example-day01-2015-0.txt"),
                Arguments.of("0", "example-day01-2015-1.txt"),
                Arguments.of("3", "example-day01-2015-2.txt"),
                Arguments.of("3", "example-day01-2015-3.txt"),
                Arguments.of("3", "example-day01-2015-4.txt"),
                Arguments.of("-1", "example-day01-2015-5.txt"),
                Arguments.of("-1", "example-day01-2015-6.txt"),
                Arguments.of("-3", "example-day01-2015-7.txt"),
                Arguments.of("-3", "example-day01-2015-8.txt")
            )
        }
    }
}