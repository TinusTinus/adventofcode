package nl.mvdr.adventofcode.adventofcode2015.day05

import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class InternElvesPart1Test: SolverTest<InternElvesPart1>(InternElvesPart1::class.java) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("1", "example-day05-2015-0.txt"),
                Arguments.of("1", "example-day05-2015-1.txt"),
                Arguments.of("2", "example-day05-2015-2.txt"),
                Arguments.of("236", "input-day05-2015.txt")
            )
        }
    }
}