package nl.mvdr.adventofcode.adventofcode2015.day11

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class CorporatePolicyPart2Test: FunctionSolverTest<String>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("cqkaabcc", "input-day11-2015.txt")
        )
    }
}
