package nl.mvdr.adventofcode.adventofcode2015.day21

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class RpgSimulatorPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("148", "input-day21-2015.txt")
        )
    }
}