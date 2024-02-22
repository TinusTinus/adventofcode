package nl.mvdr.adventofcode.adventofcode2015.day21

/** Gets all possible matchups against the given boss. */
fun getAllPossibleMatchups(boss: Boss) = getAllPossiblePlayers().map { Matchup(boss, it) }

data class Matchup(private val boss: Boss, val player: Player) {

    /** Checks whether the player wins this match-up. */
    // Note that the player is the first to attack.
    fun playerWins(): Boolean = player.turnsToKill(boss) <= boss.turnsToKill(player)
}