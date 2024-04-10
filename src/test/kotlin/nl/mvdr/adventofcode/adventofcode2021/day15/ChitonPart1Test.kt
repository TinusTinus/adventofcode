package nl.mvdr.adventofcode.adventofcode2021.day15

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ChitonPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("40", "example-day15-2021.txt"),
            Arguments.of("720", "input-day15-2021.txt")
        )
    }
}