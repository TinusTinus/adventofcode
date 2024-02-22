package nl.mvdr.adventofcode.adventofcode2015.day10

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class ElvesLookElvesSeePart1Test: FunctionSolverTest<Int>(::solvePart1) {

    @ParameterizedTest
    @MethodSource
    fun testLookAndSay(input: String, expectedOutput: String) {
        val actualOutput = lookAndSay(input)
        assertEquals(expectedOutput, actualOutput)
    }

    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("?", "input-day10-2015.txt")
        )

        @JvmStatic
        fun testLookAndSay() : List<Arguments> = listOf(
            Arguments.of("1", "11"),
            Arguments.of("11", "21"),
            Arguments.of("21", "1211"),
            Arguments.of("1211", "111221"),
            Arguments.of("111221", "312211"),
        )
    }



}