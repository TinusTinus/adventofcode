package nl.mvdr.adventofcode.adventofcode2021.day24

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ArithmeticLogicUnitPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("?", "input-day24-2021.txt")
        )
    }
}