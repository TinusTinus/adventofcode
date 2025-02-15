package nl.mvdr.adventofcode.adventofcode2015.day21

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class MatchupTest {
    @Test
    fun testMatchup() {
        val player = Player(8, 5, 5)
        val boss = Boss(12, 7, 2)
        val matchup = Matchup(boss, player)

        val result = matchup.playerWins()

        assertTrue(result)
    }
}