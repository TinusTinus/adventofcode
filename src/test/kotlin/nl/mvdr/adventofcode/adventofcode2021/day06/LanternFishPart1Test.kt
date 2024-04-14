package nl.mvdr.adventofcode.adventofcode2021.day06

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class LanternFishPart1Test: FunctionSolverTest<Long>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("5934", "example-day06-2021.txt"),
            Arguments.of("372300", "input-day06-2021.txt")
        )
    }
}