package nl.mvdr.adventofcode.adventofcode2021.day13

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class TransparentOrigamiPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("17", "example-day13-2021.txt"),
            Arguments.of("4411", "input-day13-2021.txt")
        )
    }
}