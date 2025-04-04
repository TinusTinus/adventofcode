package nl.mvdr.adventofcode.adventofcode2015.day18

import nl.mvdr.adventofcode.solver.FunctionSolver
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class LikeAGifForYourYardPart1Test {

    companion object {
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
        
        val result = solver.solve(inputFile)
        
        assertEquals(expectedSolution, result)
    }
}
