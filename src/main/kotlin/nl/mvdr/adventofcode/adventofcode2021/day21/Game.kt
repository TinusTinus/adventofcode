package nl.mvdr.adventofcode.adventofcode2021.day21

/**
 * Representation of the game.
 * Note that this class is mutable.
 */
// The players is kept in the order: [just went, up next].
class Game(private var players: List<Player>) { // TODO this might as well be a pair
    private val die = DeterministicDie()

    init {
        if (players.size != 2) {
            throw IllegalArgumentException("Expected only two players.")
        }
    }

    /**
     * Plays out the game.
     * Returns the losing player's final score, multiplied by the number of dice rolls.
     */
    fun play(): Int {
        while (players.last().score < 1000) {
            turn()
        }
        return players.first().score * die.rolls
    }

    private fun turn() {
        val spaces = die.roll() + die.roll() + die.roll()
        players = listOf(players.last(), players.first().move(spaces))
    }
}

/**
 * Parses the [lines] from an input file as the initial state of a game.
 */
fun parseGame(lines: Sequence<String>) = Game(lines.map(::parsePlayer).toList())
