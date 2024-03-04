package nl.mvdr.adventofcode.adventofcode2015.day14

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ReindeerOlympicsPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("1102", "input-day14-2015.txt")
        )
    }
}
