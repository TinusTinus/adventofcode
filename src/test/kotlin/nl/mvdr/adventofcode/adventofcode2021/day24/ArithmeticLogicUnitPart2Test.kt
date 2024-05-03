package nl.mvdr.adventofcode.adventofcode2021.day24

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.provider.Arguments

@Disabled // Test case takes a pretty long time to run
class ArithmeticLogicUnitPart2Test: FunctionSolverTest<Any>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("11841231117189", "input-day24-2021.txt")
        )
    }
}