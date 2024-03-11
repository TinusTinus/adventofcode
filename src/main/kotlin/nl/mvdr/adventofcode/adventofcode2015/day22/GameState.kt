package nl.mvdr.adventofcode.adventofcode2015.day22

/**
 * State of the game.
 */
data class GameState(private val boss: Boss, private val player: Player = Player(), private val activeEffects: Map<Effect, Int> = mapOf()) {

}

