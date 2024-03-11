package nl.mvdr.adventofcode.adventofcode2015.day22

private const val POISON_DAMAGE = 3
private const val RECHARGE_BONUS = 101
private const val SHIELD_BONUS = 7

/**
 * State of the game.
 */
data class GameState(private val boss: Boss,
        private val player: Player = Player(),
        private val activeEffects: Map<Effect, Int> = mapOf(),
        private val nextTurn: Turn = Turn.PLAYER,
        private val manaSpent: Int = 0) {

    /**
     * Returns the minimum amount of mana needed to win the game.
     * Returns null if the game cannot be won,
     * within the given [max] amount of mana.
     */
    fun manaToWin(max: Int = Int.MAX_VALUE): Int? = when {
        max < manaSpent -> null // more mana spent than the given maximum
        player.hitPoints <= 0 && boss.hitPoints <= 0 -> throw IllegalStateException("No winner")
        player.hitPoints <= 0 -> null // we lose
        boss.hitPoints <= 0 -> manaSpent // we win!
        boss.hitPoints <= POISON_DAMAGE && activeEffects.keys.contains(Effect.POISON) -> manaSpent // boss dies to poison at the start of the turn
        else -> {
            // Process spell effects.
            var updatedBossHitPoints = boss.hitPoints
            if (activeEffects.keys.contains(Effect.POISON)) {
                // Apply poison. Note that due to earlier guards, this cannot be enough to take out the boss.
                updatedBossHitPoints -= POISON_DAMAGE
            }
            var updatedPlayerMana = player.manaPoints
            if (activeEffects.keys.contains(Effect.RECHARGE)) {
                updatedPlayerMana += RECHARGE_BONUS
            }

            when(nextTurn) {
                Turn.BOSS -> null // TODO
                Turn.PLAYER -> null // TODO
            }
        }
    }
}

