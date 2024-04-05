package nl.mvdr.adventofcode.adventofcode2021.day11

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class DumboOctopusPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("195", "example-day11-2021.txt"),
            Arguments.of("364", "input-day11-2021.txt")
        )
    }
}