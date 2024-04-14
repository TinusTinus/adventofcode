package nl.mvdr.adventofcode.adventofcode2021.day18

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SnailfishPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("4140", "example-day18-2021.txt"),
            Arguments.of("?", "input-day18-2021.txt")
        )
    }
}