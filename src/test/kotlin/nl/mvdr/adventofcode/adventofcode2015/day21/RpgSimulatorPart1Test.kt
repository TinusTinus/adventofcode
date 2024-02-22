package nl.mvdr.adventofcode.adventofcode2015.day21

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class RpgSimulatorPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("78", "input-day21-2015.txt")
        )
    }
}