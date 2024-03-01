package nl.mvdr.adventofcode.adventofcode2015.day11

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class CorporatePolicyPart1Test: FunctionSolverTest<String>(::solvePart1) {
    @ParameterizedTest
    @MethodSource
    fun testIncrement(expected: String, currentPassword: String) = assertEquals(expected, increment(currentPassword))

    @ParameterizedTest
    @MethodSource
    fun testFindNextPassword(expected: String, currentPassword: String) = assertEquals(expected, findNextPassword(currentPassword))

    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("?", "input-day11-2015.txt")
        )

        @JvmStatic
        fun testIncrement(): List<Arguments> = listOf(
            Arguments.of("xy", "xx"),
            Arguments.of("xz", "xy"),
            Arguments.of("ya", "xy"),
            Arguments.of("yb", "ya")
        )

        @JvmStatic
        fun testFindNextPassword(): List<Arguments> = listOf(
            Arguments.of("abcdffaa", "abcdefgh"),
            Arguments.of("ghjaabcc", "ghijklmn"),
            Arguments.of("?", "cqjxjnds")
        )
    }
}
