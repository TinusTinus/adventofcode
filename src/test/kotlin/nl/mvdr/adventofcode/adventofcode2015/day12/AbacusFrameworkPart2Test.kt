package nl.mvdr.adventofcode.adventofcode2015.day12

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class AbacusFrameworkPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("6", "example-day12-2015-0.txt"),
            Arguments.of("4", "example-day12-2015-8.txt"),
            Arguments.of("0", "example-day12-2015-9.txt"),
            Arguments.of("6", "example-day12-2015-10.txt"),
            Arguments.of("96852", "input-day12-2015.txt")
        )
    }
}
