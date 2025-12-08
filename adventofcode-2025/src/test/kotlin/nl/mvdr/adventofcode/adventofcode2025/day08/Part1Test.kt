package nl.mvdr.adventofcode.adventofcode2025.day08

import nl.mvdr.adventofcode.solver.FunctionSolver
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Part1Test {

    @Test
    fun testExample() {
        val solver = FunctionSolver { solvePart1(it, 10) }

        val result = solver.solve("example-day08.txt")

        assertEquals("40", result)
    }
}
