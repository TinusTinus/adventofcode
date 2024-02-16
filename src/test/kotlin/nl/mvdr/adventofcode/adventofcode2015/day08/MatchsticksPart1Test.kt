package nl.mvdr.adventofcode.adventofcode2015.day08

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments
import kotlin.test.assertEquals

class MatchsticksPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {

        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("12", "example-day08-2015.txt"),
                Arguments.of("1333", "input-day08-2015.txt")
            )
        }
    }

    @Test
    fun testExample0() {
        val literal = "\"\""

        val valueLength = stringValueLength(literal)

        assertEquals(0, valueLength)
    }

    @Test
    fun testExample1() {
        val literal = "\"abc\""

        val valueLength = stringValueLength(literal)

        assertEquals(3, valueLength)
    }

    @Test
    fun testExample2() {
        val literal = "\"aaa\\\"aaa\""

        val valueLength = stringValueLength(literal)

        assertEquals(7, valueLength)
    }

    @Test
    fun testExample3() {
        val literal = "\"\\x27\""

        val valueLength = stringValueLength(literal)

        assertEquals(1, valueLength)
    }
}