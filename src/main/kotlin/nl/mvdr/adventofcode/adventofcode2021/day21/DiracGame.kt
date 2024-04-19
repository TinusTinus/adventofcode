package nl.mvdr.adventofcode.adventofcode2021.day21

private val cache = mutableMapOf<DiracGame, Map<String, Long>>()

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
        else -> {
            val result = players.toList().map(Player::name).associateWith { 0L }.toMutableMap()

            for (game in turn()) {
                val outcome = cache.getOrPut(game, game::play)
                result.entries.forEach { entry -> entry.setValue(entry.value + outcome[entry.key]!!) }
            }

            result
        }
    }

    private fun turn() = (1 .. 3)
        .flatMap { firstRoll -> (1 .. 3)
            .flatMap { secondRoll -> (1 .. 3)
                .map { thirdRoll -> firstRoll + secondRoll + thirdRoll } } }
        .map(this::move)


    private fun move(spaces: Int) = DiracGame(Pair(players.second, players.first.move(spaces)))
}