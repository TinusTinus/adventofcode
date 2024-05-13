package nl.mvdr.adventofcode.adventofcode2020.day24

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class LobbyLayoutPart2Test: FunctionSolverTest<Any>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("2208", "example-day24-2020-1.txt"),
            Arguments.of("4150", "input-day24-2020.txt")
        )
    }
}
