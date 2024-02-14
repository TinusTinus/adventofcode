package nl.mvdr.adventofcode.adventofcode2015.day06

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class FireHazardPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("1000000", "example-day06-2015-0.txt"),
                Arguments.of("1000", "example-day06-2015-1.txt"),
                Arguments.of("0", "example-day06-2015-2.txt"),
                Arguments.of("998996", "example-day06-2015-3.txt")
                // Arguments.of("569999", "input-day06-2015.txt") // disabled because this takes about a minute to compute
            )
        }
    }
}