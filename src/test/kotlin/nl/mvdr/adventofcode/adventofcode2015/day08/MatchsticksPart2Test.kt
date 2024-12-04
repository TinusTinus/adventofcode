package nl.mvdr.adventofcode.adventofcode2015.day08

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments
import kotlin.test.assertEquals

class MatchsticksPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {

        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("19", "example-day08-2015.txt")
            )
        }
    }

    @Test
    fun testExample0() {
        val literal = "\"\""

        val valueLength = encodedStringLength(literal)

        assertEquals(6, valueLength)
    }

    @Test
    fun testExample1() {
        val literal = "\"abc\""

        val valueLength = encodedStringLength(literal)

        assertEquals(9, valueLength)
    }

    @Test
    fun testExample2() {
        val literal = "\"aaa\\\"aaa\""

        val valueLength = encodedStringLength(literal)

        assertEquals(16, valueLength)
    }

    @Test
    fun testExample3() {
        val literal = "\"\\x27\""

        val valueLength = encodedStringLength(literal)

        assertEquals(11, valueLength)
    }
}