package nl.mvdr.adventofcode.adventofcode2021.day07

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class TreacheryOfWhalesPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("37", "example-day07-2021.txt")
        )
    }
}