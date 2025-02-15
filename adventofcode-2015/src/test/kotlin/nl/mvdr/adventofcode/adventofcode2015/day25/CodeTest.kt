package nl.mvdr.adventofcode.adventofcode2015.day25

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CodeTest {

    @ParameterizedTest
    @MethodSource
    fun testCode(row: Int, column: Int, expectedCode: Long) = assertEquals(expectedCode, getCode(row, column))

    companion object {
        @JvmStatic
        fun testCode(): List<Arguments> = listOf(
            Arguments.of(1, 1, 20151125),
            Arguments.of(1, 2, 18749137),
            Arguments.of(1, 3, 17289845),
            Arguments.of(1, 4, 30943339),
            Arguments.of(1, 5, 10071777),
            Arguments.of(1, 6, 33511524),

            Arguments.of(2, 1, 31916031),
            Arguments.of(2, 2, 21629792),
            Arguments.of(2, 3, 16929656),
            Arguments.of(2, 4, 7726640),
            Arguments.of(2, 5, 15514188),
            Arguments.of(2, 6, 4041754),

            Arguments.of(3, 1, 16080970),
            Arguments.of(3, 2, 8057251),
            Arguments.of(3, 3, 1601130),
            Arguments.of(3, 4, 7981243),
            Arguments.of(3, 5, 11661866),
            Arguments.of(3, 6, 16474243),

            Arguments.of(4, 1, 24592653),
            Arguments.of(4, 2, 32451966),
            Arguments.of(4, 3, 21345942),
            Arguments.of(4, 4, 9380097),
            Arguments.of(4, 5, 10600672),
            Arguments.of(4, 6, 31527494),

            Arguments.of(5, 1, 77061),
            Arguments.of(5, 2, 17552253),
            Arguments.of(5, 3, 28094349),
            Arguments.of(5, 4, 6899651),
            Arguments.of(5, 5, 9250759),
            Arguments.of(5, 6, 31663883),

            Arguments.of(6, 1, 33071741),
            Arguments.of(6, 2, 6796745),
            Arguments.of(6, 3, 25397450),
            Arguments.of(6, 4, 24659492),
            Arguments.of(6, 5, 1534922),
            Arguments.of(6, 6, 27995004)
        )
    }
}