package nl.mvdr.adventofcode.adventofcode2015.day02

import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class NoMathPart2Test: SolverTest<NoMathPart2>(NoMathPart2::class.java) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("34", "example-day02-2015-0.txt"),
                Arguments.of("14", "example-day02-2015-1.txt"),
                Arguments.of("3783758", "input-day02-2015.txt") // 917 is too low!
            )
        }
    }
}