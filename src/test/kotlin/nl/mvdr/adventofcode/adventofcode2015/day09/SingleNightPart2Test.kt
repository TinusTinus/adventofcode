package nl.mvdr.adventofcode.adventofcode2015.day09

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SingleNightPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {

        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("982", "example-day09-2015.txt"),
                Arguments.of("804", "input-day09-2015.txt")
            )
        }
    }
}