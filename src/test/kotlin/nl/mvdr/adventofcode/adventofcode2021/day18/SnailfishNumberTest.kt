package nl.mvdr.adventofcode.adventofcode2021.day18

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class SnailfishNumberTest {

    @ParameterizedTest
    @MethodSource
    fun testMagnitude(snailfishNumberText: String, magnitude: Int) {
        val number = parseSnailfishNumber(snailfishNumberText)

        val result = number.magnitude()

        assertEquals(magnitude, result)
    }

    @ParameterizedTest
    @MethodSource
    fun testPlus(leftText: String, rightText: String, sumText: String) {
        val left = parseSnailfishNumber(leftText)
        val right = parseSnailfishNumber(rightText)
        val sum = parseSnailfishNumber(sumText)

        val result = left + right

        assertEquals(sum, result)
    }

    companion object {
        @JvmStatic
        fun testMagnitude(): List<Arguments> = listOf(
            Arguments.of("[9,1]", 29),
            Arguments.of("[1,9]", 21),
            Arguments.of("[[9,1],[1,9]]", 129),
            Arguments.of("[[1,2],[[3,4],5]]", 143),
            Arguments.of("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", 1384),
            Arguments.of("[[[[1,1],[2,2]],[3,3]],[4,4]]", 445),
            Arguments.of("[[[[3,0],[5,3]],[4,4]],[5,5]]", 791),
            Arguments.of("[[[[5,0],[7,4]],[5,5]],[6,6]]", 1137),
            Arguments.of("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", 3488),
            Arguments.of("[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]", 4140)
        )

        @JvmStatic
        fun testPlus(): List<Arguments> = listOf(
            Arguments.of("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]", "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),
            Arguments.of("[]", "[]", "[]"),

        )
    }
}