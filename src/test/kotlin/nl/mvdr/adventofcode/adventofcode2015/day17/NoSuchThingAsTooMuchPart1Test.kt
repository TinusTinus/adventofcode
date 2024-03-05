package nl.mvdr.adventofcode.adventofcode2015.day17

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class NoSuchThingAsTooMuchPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("4", "example-day17-2015.txt"),
            Arguments.of("?", "input-day17-2015.txt")
        )
    }
}
