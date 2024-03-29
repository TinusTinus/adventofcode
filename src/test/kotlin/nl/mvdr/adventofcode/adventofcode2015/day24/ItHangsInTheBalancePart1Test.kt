package nl.mvdr.adventofcode.adventofcode2015.day24

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ItHangsInTheBalancePart1Test: FunctionSolverTest<Long>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("99", "example-day24-2015.txt"),
            Arguments.of("10723906903", "input-day24-2015.txt")
        )
    }
}