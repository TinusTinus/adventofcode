package nl.mvdr.adventofcode.adventofcode2021.day21

/**
 * Representation of a game between two [players].
 */
// The players are kept in the order: (just went, up next).
data class DiracGame(val players: Pair<Player, Player>) {

    /**
     * Plays out the game.
     * Return a map containing, per player name, the number of universes in which the player won.
     */
    fun play(): Map<String, Long> = when {
        21 <= players.second.score -> mapOf(Pair(players.first.name, 0L), Pair(players.second.name, 1L))
        else -> emptyMap() // TODO roll the dice and play a turn for each universe
    }
}