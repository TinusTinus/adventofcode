package nl.mvdr.adventofcode.adventofcode2015.day22

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class WizardSimulatorPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("?", "input-day22-2015.txt") // 2445 is too high, 1700 seems too low
        )
    }
}