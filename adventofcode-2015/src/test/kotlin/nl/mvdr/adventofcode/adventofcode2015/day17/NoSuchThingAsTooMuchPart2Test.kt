package nl.mvdr.adventofcode.adventofcode2015.day17

import nl.mvdr.adventofcode.solver.FunctionSolver
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NoSuchThingAsTooMuchPart2Test {
    @Test
    fun testExample() {
        val solver = FunctionSolver { solvePart2(it, 25) }
        
        val result = solver.solve("example-day17-2015.txt")

        assertEquals("3", result)
    }
}
