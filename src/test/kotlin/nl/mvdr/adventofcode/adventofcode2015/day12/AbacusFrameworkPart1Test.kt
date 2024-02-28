package nl.mvdr.adventofcode.adventofcode2015.day12

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class AbacusFrameworkPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("6", "example-day12-2015-0.txt"),
            Arguments.of("6", "example-day12-2015-1.txt"),
            Arguments.of("3", "example-day12-2015-2.txt"),
            Arguments.of("3", "example-day12-2015-3.txt"),
            Arguments.of("0", "example-day12-2015-4.txt"),
            Arguments.of("0", "example-day12-2015-5.txt"),
            Arguments.of("0", "example-day12-2015-6.txt"),
            Arguments.of("0", "example-day12-2015-7.txt"),
            Arguments.of("?", "input-day12-2015.txt")
        )
    }
}
