package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class AmphipodPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("12521", "example-day23-2021.txt"),
            Arguments.of("?", "input-day23-2021.txt") // 10466 is too low
        )
    }
}