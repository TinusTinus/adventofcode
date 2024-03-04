package nl.mvdr.adventofcode.adventofcode2015.day16

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class AuntSuePart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("260", "input-day16-2015.txt")
        )
    }
}
