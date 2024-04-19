package nl.mvdr.adventofcode.adventofcode2021.day21

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class DiracDicePart2Test: FunctionSolverTest<Any>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("444356092776315", "example-day21-2021.txt"),
            Arguments.of("919758187195363", "input-day21-2021.txt")
        )
    }
}