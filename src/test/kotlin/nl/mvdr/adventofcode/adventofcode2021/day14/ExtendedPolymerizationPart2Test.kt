package nl.mvdr.adventofcode.adventofcode2021.day14

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ExtendedPolymerizationPart2Test: FunctionSolverTest<Long>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("2188189693529", "example-day14-2021.txt"),
            Arguments.of("3353146900153", "input-day14-2021.txt")
        )
    }
}