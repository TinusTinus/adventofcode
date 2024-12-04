package nl.mvdr.adventofcode.adventofcode2020.day25

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ComboBreakerPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("14897079", "example-day25-2020.txt")
        )
    }
}
