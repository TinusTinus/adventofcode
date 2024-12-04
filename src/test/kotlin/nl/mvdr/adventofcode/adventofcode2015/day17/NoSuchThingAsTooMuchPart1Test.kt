package nl.mvdr.adventofcode.adventofcode2015.day17

import nl.mvdr.adventofcode.FunctionSolver
import org.junit.jupiter.api.Test

class NoSuchThingAsTooMuchPart1Test {
    @Test
    fun testExample() {
        val solver = FunctionSolver { solvePart1(it, 25) }
        val inputFile = "example-day17-2015.txt"

        assertSolution(solver, "4", inputFile)
    }
}
