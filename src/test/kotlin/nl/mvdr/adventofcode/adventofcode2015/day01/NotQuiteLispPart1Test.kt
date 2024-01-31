package nl.mvdr.adventofcode.adventofcode2015.day01

import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class NotQuiteLispPart1Test: SolverTest<NotQuiteLispPart1>(NotQuiteLispPart1::class.java) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(Arguments.of("280", "input-day01-2015.txt"))
        }
    }
}