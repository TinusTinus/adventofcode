package nl.mvdr.adventofcode.adventofcode2015.day18

import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LikeAGifForYourYardPart2Test: FunctionSolverTest<Int>(::solvePart2) {

    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("1006", "input-day18-2015.txt")
        )

        @JvmStatic
        fun testExample(): List<Arguments> = listOf(
            Arguments.of(0, "17"),
            Arguments.of(1, "18"),
            Arguments.of(2, "18"),
            Arguments.of(3, "18"),
            Arguments.of(4, "14"),
            Arguments.of(5, "17"),
        )
    }

    @ParameterizedTest
    @MethodSource
    fun testExample(steps: Int, expectedSolution: String) {
        val solver = FunctionSolver { solvePart2(it, steps) }
        val inputFile = "example-day18-2015.txt"

        assertSolution(solver, expectedSolution, inputFile)
    }
}
