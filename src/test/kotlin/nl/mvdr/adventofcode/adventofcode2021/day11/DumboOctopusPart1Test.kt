package nl.mvdr.adventofcode.adventofcode2021.day11

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class DumboOctopusPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("1656", "example-day11-2021.txt"),
            Arguments.of("1743", "input-day11-2021.txt")
        )
    }
}