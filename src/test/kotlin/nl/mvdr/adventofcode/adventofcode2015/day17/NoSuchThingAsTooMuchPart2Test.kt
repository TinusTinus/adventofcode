package nl.mvdr.adventofcode.adventofcode2015.day17

import nl.mvdr.adventofcode.FunctionSolver
import org.junit.jupiter.api.Test

class NoSuchThingAsTooMuchPart2Test {
    @Test
    fun testExample() {
        val solver = FunctionSolver { solvePart2(it, 25) }
        val inputFile = "example-day17-2015.txt"

        assertSolution(solver, "3", inputFile)
    }
}
