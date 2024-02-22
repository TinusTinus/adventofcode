package nl.mvdr.adventofcode.adventofcode2015.day21

class Matchup(private val boss: Boss, val player: Player) {

    /** Checks whether the player wins this match-up. */
    // Note that the player is the first to attack.
    fun playerWins(): Boolean = player.turnsToKill(boss) <= boss.turnsToKill(player)
}