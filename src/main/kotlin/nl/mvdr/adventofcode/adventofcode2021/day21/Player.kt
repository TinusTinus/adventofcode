package nl.mvdr.adventofcode.adventofcode2021.day21

import io.github.oshai.kotlinlogging.KotlinLogging

/**
 * Representation of a player in the game.
 */
data class Player(private val name: String, val position: Int, val score: Int = 0) {
    fun move(spaces: Int): Player {
        val newPosition = (position + spaces - 1) % 10 + 1
        val newScore = score + newPosition
        return Player(name, newPosition, newScore)
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