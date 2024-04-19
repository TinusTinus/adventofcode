package nl.mvdr.adventofcode.adventofcode2021.day21

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class PlayerTest {
    @ParameterizedTest
    @MethodSource
    fun testMove(startingPosition: Int, startingScore: Int, spacesToMove: Int, expectedPosition: Int, expectedScore: Int) {
        val player = Player("Test Player", startingPosition, startingScore)

        player.move(spacesToMove)

        assertEquals(expectedPosition, player.position)
        assertEquals(expectedScore, player.score)
    }

    companion object {
        @JvmStatic
        fun testMove() = listOf(
            Arguments.of(4, 0, 1 + 2 + 3, 10, 10),
            Arguments.of(8, 0, 4 + 5 + 6, 3, 3),
            Arguments.of(10, 10, 7 + 8 + 9, 4, 14),
            Arguments.of(3, 3, 10 + 11 + 12, 6, 9),
            Arguments.of(4, 14, 13 + 14 + 15, 6, 20),
            Arguments.of(6, 9, 16 + 17 + 18, 7, 16),
            Arguments.of(6, 20, 19 + 20 + 21, 6, 26),
            Arguments.of(7, 16, 22 + 23 + 24, 6, 22)
        )
    }
}