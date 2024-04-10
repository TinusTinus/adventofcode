package nl.mvdr.adventofcode.adventofcode2021.day15

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ChitonPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("315", "example-day15-2021.txt"),
            Arguments.of("3025", "input-day15-2021.txt")
        )
    }
}