package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class AmphipodPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("12521", "example-day23-2021.txt")
        )
    }
}