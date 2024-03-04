package nl.mvdr.adventofcode.adventofcode2015.day14

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ReindeerOlympicsPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("?", "input-day14-2015.txt") // 2660 is too high
        )
    }
}
