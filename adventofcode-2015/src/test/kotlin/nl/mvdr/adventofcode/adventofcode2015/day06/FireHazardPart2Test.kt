package nl.mvdr.adventofcode.adventofcode2015.day06

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class FireHazardPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("1", "example-day06-2015-4.txt"),
                Arguments.of("2000000", "example-day06-2015-5.txt"),
                Arguments.of("1000000", "example-day06-2015-0.txt"),
                Arguments.of("2000", "example-day06-2015-1.txt")
            )
        }
    }
}