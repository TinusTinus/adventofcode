package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.provider.Arguments

@Disabled // These test cases take about one minute each to run.
class AmphipodPart2Test: FunctionSolverTest<Any>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("44169", "example-day23-2021.txt"),
            Arguments.of("48304", "input-day23-2021.txt")
        )
    }
}