package nl.mvdr.adventofcode.adventofcode2021.day12

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class PassagePathingPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("36", "example-day12-2021-0.txt"),
            Arguments.of("103", "example-day12-2021-1.txt"),
            Arguments.of("3509", "example-day12-2021-2.txt")
        )
    }
}