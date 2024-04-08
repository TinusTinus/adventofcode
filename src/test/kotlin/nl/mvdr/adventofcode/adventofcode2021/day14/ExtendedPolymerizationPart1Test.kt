package nl.mvdr.adventofcode.adventofcode2021.day14

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ExtendedPolymerizationPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("1588", "example-day14-2021.txt"),
            Arguments.of("2915", "input-day14-2021.txt")
        )
    }
}