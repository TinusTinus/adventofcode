package nl.mvdr.adventofcode.adventofcode2015.day24

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ItHangsInTheBalancePart2Test: FunctionSolverTest<Long>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("44", "example-day24-2015.txt"),
            Arguments.of("74850409", "input-day24-2015.txt")
        )
    }
}