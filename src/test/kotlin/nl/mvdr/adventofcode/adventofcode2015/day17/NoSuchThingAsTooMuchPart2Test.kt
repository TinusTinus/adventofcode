package nl.mvdr.adventofcode.adventofcode2015.day17

import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments

class NoSuchThingAsTooMuchPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("17", "input-day17-2015.txt")
        )
    }

    @Test
    fun testExample() {
        val solver = FunctionSolver { solvePart2(it, 25) }
        val inputFile = "example-day17-2015.txt"

        assertSolution(solver, "3", inputFile)
    }
}
