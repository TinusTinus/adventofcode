package nl.mvdr.adventofcode.adventofcode2021.day17

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class TrickShotPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("45", "example-day17-2021.txt")
        )
    }
}