package nl.mvdr.adventofcode.adventofcode2015.day09

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SingleNightPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {

        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("605", "example-day09-2015.txt"),
                Arguments.of("?", "input-day09-2015.txt")
            )
        }
    }
}