package nl.mvdr.adventofcode.adventofcode2021.day17

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class TrickShotPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("112", "example-day17-2021.txt"),
            Arguments.of("?", "input-day17-2021.txt")
        )
    }
}