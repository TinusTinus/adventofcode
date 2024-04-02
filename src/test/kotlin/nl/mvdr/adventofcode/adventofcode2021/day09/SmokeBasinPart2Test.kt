package nl.mvdr.adventofcode.adventofcode2021.day09

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SmokeBasinPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("1134", "example-day09-2021.txt"),
            Arguments.of("?", "input-day09-2021.txt")
        )
    }
}