package nl.mvdr.adventofcode.adventofcode2015.day23

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class OpeningTheTuringLockPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("0", "example-day23-2015.txt"),
            Arguments.of("231", "input-day23-2015.txt")
        )
    }
}