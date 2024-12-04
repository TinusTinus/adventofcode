package nl.mvdr.adventofcode.adventofcode2019.day18

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ManyWorldsInterpretationPart2Test: FunctionSolverTest<Any>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("8", "example-day18-2019-7.txt"),
            Arguments.of("24", "example-day18-2019-8.txt"),
            Arguments.of("32", "example-day18-2019-9.txt"),
            Arguments.of("72", "example-day18-2019-10.txt")
        )
    }
}
