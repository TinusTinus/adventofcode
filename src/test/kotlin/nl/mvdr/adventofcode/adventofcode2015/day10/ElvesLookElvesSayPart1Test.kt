package nl.mvdr.adventofcode.adventofcode2015.day10

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ElvesLookElvesSayPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("329356", "input-day10-2015.txt")
        )
    }
}
