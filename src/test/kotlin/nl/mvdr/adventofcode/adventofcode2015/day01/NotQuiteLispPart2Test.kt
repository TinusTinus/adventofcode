package nl.mvdr.adventofcode.adventofcode2015.day01

import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class NotQuiteLispPart2Test: SolverTest<NotQuiteLispPart2>(NotQuiteLispPart2::class.java) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("1", "example-day01-2015-9.txt"),
                Arguments.of("5", "example-day01-2015-10.txt"),
                Arguments.of("1797", "input-day01-2015.txt")
            )
        }
    }
}