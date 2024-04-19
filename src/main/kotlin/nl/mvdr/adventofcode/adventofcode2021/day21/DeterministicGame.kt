package nl.mvdr.adventofcode.adventofcode2021.day21

/**
 * Representation of a game between two [players].
 * Note that this class is mutable.
 */
// The players are kept in the order: (just went, up next).
class DeterministicGame(private var players: Pair<Player, Player>) {
    private val die = DeterministicDie()

    /**
     * Plays out the game.
     * Returns the losing player's final score, multiplied by the number of dice rolls.
     */
    fun play(): Int {
        while (players.second.score <= 1000) {
            turn()
        }
        return players.first.score * die.rolls
    }

    private fun turn() {
        val spaces = die.roll() + die.roll() + die.roll()
        players = Pair(players.second, players.first.move(spaces))
    }
}
