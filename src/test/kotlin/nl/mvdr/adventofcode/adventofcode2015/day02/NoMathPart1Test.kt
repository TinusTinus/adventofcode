package nl.mvdr.adventofcode.adventofcode2015.day02

import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class NoMathPart1Test: SolverTest<NoMathPart1>(NoMathPart1::class.java) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("58", "example-day02-2015-0.txt"),
                Arguments.of("43", "example-day02-2015-1.txt"),
                Arguments.of("1588178", "input-day02-2015.txt")
            )
        }
    }
}