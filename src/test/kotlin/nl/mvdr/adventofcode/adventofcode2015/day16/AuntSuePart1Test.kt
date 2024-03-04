package nl.mvdr.adventofcode.adventofcode2015.day16

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class AuntSuePart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("373", "input-day16-2015.txt")
        )
    }
}
