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
     * Returns an updated game state, after the boss takes his turn.
     */
    private fun performBossAttack(): GameState {
        if (nextTurn != Turn.BOSS) {
            throw IllegalStateException("It is not the boss's turn.")
        }

        // Process any spell effects.
        // Poison
        val updatedBossHitPoints = boss.hitPoints - when {
                    activeEffects.keys.contains(Effect.POISON) -> POISON_DAMAGE
                    else -> 0
                }
        if (updatedBossHitPoints <= 0) {
            throw IllegalArgumentException("Boss dies at the start of the turn and does not get to act.")
        }
        // Recharge
        val updatedPlayerMana = player.manaPoints + when {
            activeEffects.keys.contains(Effect.RECHARGE) -> RECHARGE_BONUS
            else -> 0
        }
        // Shield
        val playerArmor = when {
            activeEffects.keys.contains(Effect.SHIELD) -> SHIELD_BONUS
            else -> 0
        }
        val damage = (boss.damage - playerArmor).coerceAtLeast(1)
        val updatedPlayerHitPoints = (player.hitPoints - damage).coerceAtLeast(0)

        val updatedActiveEffects = activeEffects.mapValues { it.value - 1 }.filterValues { 0 < it }

        return GameState(Boss(updatedBossHitPoints, boss.damage),
            Player(updatedPlayerHitPoints, updatedPlayerMana),
            updatedActiveEffects,
            Turn.PLAYER,
            manaSpent)
    }

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
        else -> null // TODO
    }
}

