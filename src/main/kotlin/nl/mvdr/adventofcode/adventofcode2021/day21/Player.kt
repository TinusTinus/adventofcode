package nl.mvdr.adventofcode.adventofcode2021.day21

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger{}

/**
 * Representation of a player in the game.
 * Note that this class is mutable.
 */
class Player(private val name: String, position: Int, score: Int = 0) {
    var position: Int = position
        private set
    var score: Int = score
        private set

    fun move(spaces: Int) {
        position = (position + spaces - 1) % 10 + 1
        score += position
        logger.debug { "$name moved $spaces spaces to position $position. New score: $score"}
    }
}

/**
 * Parses [text] from the puzzle input as the string representation of a player.
 * For example: "Player 1 starting position: 4"
 */
fun parsePlayer(text: String): Player {
    val (name, startingPosition) = text.split(" starting position: ")
    return Player(name, startingPosition.toInt())
}