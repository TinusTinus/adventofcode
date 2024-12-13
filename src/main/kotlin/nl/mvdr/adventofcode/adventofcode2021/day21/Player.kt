package nl.mvdr.adventofcode.adventofcode2021.day21

/**
 * Representation of a player in the game.
 */
data class Player(val name: String, val position: Int, val score: Int = 0) {
    fun move(spaces: Int): Player {
        val newPosition = (position + spaces - 1) % 10 + 1
        val newScore = score + newPosition
        return Player(name, newPosition, newScore)
    }
}

/**
 * Parses the [lines] from the puzzle input as a pair of players.
 */
fun parsePlayers(lines: Sequence<String>) = lines.map(::parsePlayer).zipWithNext().first()

/**
 * Parses [text] from the puzzle input as the string representation of a player.
 * For example: "Player 1 starting position: 4"
 */
private fun parsePlayer(text: String): Player {
    val (name, startingPosition) = text.split(" starting position: ")
    return Player(name, startingPosition.toInt())
}