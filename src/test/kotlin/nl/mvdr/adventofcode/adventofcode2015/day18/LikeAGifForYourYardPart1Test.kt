package nl.mvdr.adventofcode.adventofcode2015.day18

import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LikeAGifForYourYardPart1Test: FunctionSolverTest<Int>(::solvePart1) {

    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("1638", "input-day18-2015.txt")
        )

        @JvmStatic
        fun testExample(): List<Arguments> = listOf(
            Arguments.of(0, "15"),
            Arguments.of(1, "11"),
            Arguments.of(2, "8"),
            Arguments.of(3, "4"),
            Arguments.of(4, "4")
        )
    }

    @ParameterizedTest
    @MethodSource
    fun testExample(steps: Int, expectedSolution: String) {
        val solver = FunctionSolver { solvePart1(it, steps) }
        val inputFile = "example-day18-2015.txt"

        assertSolution(solver, expectedSolution, inputFile)
    }
}
