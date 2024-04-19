package nl.mvdr.adventofcode.adventofcode2021.day21

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class DiracDicePart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("?", "example-day21-2021.txt"),
            Arguments.of("?", "input-day21-2021.txt")
        )
    }
}