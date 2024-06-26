package nl.mvdr.adventofcode.adventofcode2021.day07

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class TreacheryOfWhalesPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("168", "example-day07-2021.txt"),
            Arguments.of("92439766", "input-day07-2021.txt")
        )
    }
}