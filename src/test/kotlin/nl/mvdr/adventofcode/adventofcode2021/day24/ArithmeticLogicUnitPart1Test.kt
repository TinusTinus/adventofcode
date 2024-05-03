package nl.mvdr.adventofcode.adventofcode2021.day24

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.provider.Arguments

@Disabled // Test case takes a pretty long time to run
class ArithmeticLogicUnitPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("12996997829399", "input-day24-2021.txt")
        )
    }
}