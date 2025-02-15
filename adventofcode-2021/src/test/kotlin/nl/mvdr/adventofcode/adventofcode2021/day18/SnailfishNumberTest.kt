package nl.mvdr.adventofcode.adventofcode2021.day18

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class SnailfishNumberTest {

    @ParameterizedTest
    @MethodSource
    fun testMagnitude(expectedMagnitude: Int, snailfishNumberText: String) {
        val number = parseSnailfishNumber(snailfishNumberText)

        val result = number.magnitude()

        assertEquals(expectedMagnitude, result)
    }

    @ParameterizedTest
    @MethodSource
    fun testPlus(expectedSumText: String, vararg snailfishNumberText: String) {
        val sum = parseSnailfishNumber(expectedSumText)
        val numbers = snailfishNumberText.map(::parseSnailfishNumber)

        val result = numbers.reduce(SnailfishNumber::plus)

        assertEquals(sum, result)
    }

    @ParameterizedTest
    @MethodSource
    fun testExplode(expectedResultText: String, numberText: String) {
        val number = parseSnailfishNumber(numberText)
        val expectedResult = parseSnailfishNumber(expectedResultText)

        val result = number.explode()

        assertEquals(expectedResult, result)
    }

    companion object {
        @JvmStatic
        fun testMagnitude() = listOf(
            Arguments.of(29, "[9,1]"),
            Arguments.of(21, "[1,9]"),
            Arguments.of(129, "[[9,1],[1,9]]"),
            Arguments.of(143, "[[1,2],[[3,4],5]]"),
            Arguments.of(1384, "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"),
            Arguments.of(445, "[[[[1,1],[2,2]],[3,3]],[4,4]]"),
            Arguments.of(791, "[[[[3,0],[5,3]],[4,4]],[5,5]]"),
            Arguments.of(1137, "[[[[5,0],[7,4]],[5,5]],[6,6]]"),
            Arguments.of(3488, "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"),
            Arguments.of(4140, "[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]")
        )

        @JvmStatic
        fun testPlus() = listOf(
            Arguments.of("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", arrayOf("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]")),
            Arguments.of("[[[[1,1],[2,2]],[3,3]],[4,4]]", arrayOf("[1,1]", "[2,2]", "[3,3]", "[4,4]")),
            Arguments.of("[[[[3,0],[5,3]],[4,4]],[5,5]]", arrayOf("[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]")),
            Arguments.of("[[[[5,0],[7,4]],[5,5]],[6,6]]", arrayOf("[1,1]", "[2,2]", "[3,3]", "[4,4]", "[5,5]", "[6,6]")),
            Arguments.of("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]", arrayOf("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]", "[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]")),
            Arguments.of("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]", arrayOf("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]", "[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]")),
            Arguments.of("[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]", arrayOf("[[[[6,7],[6,7]],[[7,7],[0,7]]],[[[8,7],[7,7]],[[8,8],[8,0]]]]", "[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]")),
            Arguments.of("[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]", arrayOf("[[[[7,0],[7,7]],[[7,7],[7,8]]],[[[7,7],[8,8]],[[7,7],[8,7]]]]", "[7,[5,[[3,8],[1,4]]]]")),
            Arguments.of("[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]", arrayOf("[[[[7,7],[7,8]],[[9,5],[8,7]]],[[[6,8],[0,8]],[[9,9],[9,0]]]]", "[[2,[2,2]],[8,[8,1]]]")),
            Arguments.of("[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]", arrayOf("[[[[6,6],[6,6]],[[6,0],[6,7]]],[[[7,7],[8,9]],[8,[8,1]]]]", "[2,9]")),
            Arguments.of("[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]", arrayOf("[[[[6,6],[7,7]],[[0,7],[7,7]]],[[[5,5],[5,6]],9]]", "[1,[[[9,3],9],[[9,0],[0,7]]]]")),
            Arguments.of("[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]", arrayOf("[[[[7,8],[6,7]],[[6,8],[0,8]]],[[[7,7],[5,0]],[[5,5],[5,6]]]]", "[[[5,[7,4]],7],1]")),
            Arguments.of("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]", arrayOf("[[[[7,7],[7,7]],[[8,7],[8,7]]],[[[7,0],[7,7]],9]]", "[[[[4,2],2],6],[8,7]]"))
        )

        @JvmStatic
        fun testExplode() = listOf(
            Arguments.of("[[[[0,9],2],3],4]", "[[[[[9,8],1],2],3],4]"),
            Arguments.of("[7,[6,[5,[7,0]]]]", "[7,[6,[5,[4,[3,2]]]]]"),
            Arguments.of("[[6,[5,[7,0]]],3]", "[[6,[5,[4,[3,2]]]],1]"),
            Arguments.of("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]"),
            Arguments.of("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"),
            Arguments.of("[[[[0,7],4],[7,[[8,4],9]]],[1,1]]", "[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]"),
            Arguments.of("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", "[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]")
        )

    }
}