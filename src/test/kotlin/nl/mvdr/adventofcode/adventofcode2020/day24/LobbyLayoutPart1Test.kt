package nl.mvdr.adventofcode.adventofcode2020.day24

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class LobbyLayoutPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("2", "example-day24-2020-0.txt"),
            Arguments.of("10", "example-day24-2020-1.txt"),
            Arguments.of("459", "input-day24-2020.txt")
        )
    }
}
