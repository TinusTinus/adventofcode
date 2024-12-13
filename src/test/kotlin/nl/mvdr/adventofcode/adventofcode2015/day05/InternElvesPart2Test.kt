package nl.mvdr.adventofcode.adventofcode2015.day05

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments
import kotlin.test.assertEquals

class InternElvesPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("2", "example-day05-2015-3.txt")
            )
        }
    }

    @Test
    fun qjhvhtzxzqqjkmpb() {
        assertEquals(1, solvePart2(sequenceOf("qjhvhtzxzqqjkmpb")))
    }

    @Test
    fun xxyxx() {
        assertEquals(1, solvePart2(sequenceOf("xxyxx")))
    }

    @Test
    fun uurcxstgmygtbstg() {
        assertEquals(0, solvePart2(sequenceOf("uurcxstgmygtbstg")))
    }

    @Test
    fun ieodomkazucvgmuy() {
        assertEquals(0, solvePart2(sequenceOf("ieodomkazucvgmuy")))
    }
}