package nl.mvdr.adventofcode.adventofcode2015.day22

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class WizardSimulatorPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("1289", "input-day22-2015.txt")
        )
    }
}