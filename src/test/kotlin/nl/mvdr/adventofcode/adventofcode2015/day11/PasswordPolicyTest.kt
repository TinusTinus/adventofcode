package nl.mvdr.adventofcode.adventofcode2015.day11

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class PasswordPolicyTest {
    @ParameterizedTest
    @MethodSource
    fun testIncrement(expected: String, currentPassword: String) = assertEquals(expected, increment(currentPassword))

    @ParameterizedTest
    @MethodSource
    fun testFirstRequirement(expected: Boolean, currentPassword: String) = assertEquals(expected, meetsFirstRequirement(currentPassword))

    @ParameterizedTest
    @MethodSource
    fun testSecondRequirement(expected: Boolean, currentPassword: String) = assertEquals(expected, meetsSecondRequirement(currentPassword))

    @ParameterizedTest
    @MethodSource
    fun testThirdRequirement(expected: Boolean, currentPassword: String) = assertEquals(expected, meetsThirdRequirement(currentPassword))

    @ParameterizedTest
    @MethodSource
    fun testIsValid(expected: Boolean, currentPassword: String) = assertEquals(expected, isValidPassword(currentPassword))

    @ParameterizedTest
    @MethodSource
    fun testFindNextPassword(expected: String, currentPassword: String) = assertEquals(expected, findNextPassword(currentPassword))

    companion object {
        @JvmStatic
        fun testIncrement(): List<Arguments> = listOf(
            Arguments.of("xy", "xx"),
            Arguments.of("xz", "xy"),
            Arguments.of("ya", "xz"),
            Arguments.of("yb", "ya")
        )

        @JvmStatic
        fun testFirstRequirement(): List<Arguments> = listOf(
            Arguments.of(true, "hijklmmn"),
            Arguments.of(false, "abbceffg"),
            Arguments.of(true, "abcdffaa"),
            Arguments.of(true, "ghjaabcc")
        )

        @JvmStatic
        fun testSecondRequirement(): List<Arguments> = listOf(
            Arguments.of(false, "hijklmmn"),
            Arguments.of(true, "abcdffaa"),
            Arguments.of(true, "ghjaabcc")
        )

        @JvmStatic
        fun testThirdRequirement(): List<Arguments> = listOf(
            Arguments.of(true, "abbceffg"),
            Arguments.of(false, "abbcegjk"),
            Arguments.of(true, "abcdffaa"),
            Arguments.of(true, "ghjaabcc")
        )

        @JvmStatic
        fun testIsValid(): List<Arguments> = listOf(
            Arguments.of(false, "hijklmmn"),
            Arguments.of(false, "abbceffg"),
            Arguments.of(false, "abbcegjk"),
            Arguments.of(true, "abcdffaa"),
            Arguments.of(true, "ghjaabcc"),
            Arguments.of(true, "cqjxxyzz"),
        )

        @JvmStatic
        fun testFindNextPassword(): List<Arguments> = listOf(
            Arguments.of("abcdffaa", "abcdefgh"),
            Arguments.of("ghjaabcc", "ghijklmn"),
            Arguments.of("cqjxxyzz", "cqjxjnds")
        )
    }
}
