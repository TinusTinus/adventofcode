package nl.mvdr.adventofcode.adventofcode2021.day25

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.provider.Arguments

@Disabled // Test case takes a pretty long time to run
class SeaCucumberPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("58", "example-day25-2021.txt"),
            Arguments.of("?", "input-day25-2021.txt")
        )
    }
}