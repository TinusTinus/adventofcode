package nl.mvdr.adventofcode.adventofcode2015.day22

import io.github.oshai.kotlinlogging.KotlinLogging

private const val POISON_DAMAGE = 3
private const val RECHARGE_BONUS = 101
private const val SHIELD_BONUS = 7

private val logger = KotlinLogging.logger{}

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
        boss.hitPoints <= poisonDamage() -> manaSpent // boss dies to poison at the start of the turn, so we win!
        nextTurn == Turn.BOSS -> performBossAttack().manaToWin(max)
        nextTurn == Turn.PLAYER -> {
            // Player's turn
            var result: Int? = null
            for (spell in Spell.entries)  {
                if (canCast(spell)) {
                    val resultForSpell = cast(spell).manaToWin(result ?: max)
                    if (resultForSpell != null && (result == null || resultForSpell < result)) {
                        logger.debug { "Boss can be defeated using $resultForSpell mana points" }
                        result = resultForSpell
                    }
                }
            }
            result
        }
        else -> throw IllegalStateException()
    }

    /**
     * Returns an updated game state, after the boss takes his turn.
     *
     * This method assumes it is currently the boss' turn!
     */
    private fun performBossAttack(): GameState {
        // Process any spell effects.
        val updatedBossHitPoints = boss.hitPoints - poisonDamage()
        val updatedPlayerMana = player.manaPoints + rechargeBonus()
        val armor = getPlayerArmor()

        // Process boss' attack
        val damage = (boss.damage - armor).coerceAtLeast(1)
        val updatedPlayerHitPoints = player.hitPoints - damage

        return GameState(Boss(updatedBossHitPoints, boss.damage),
            Player(updatedPlayerHitPoints, updatedPlayerMana),
            activeEffects.mapValues { it.value - 1 }.filterValues { 0 < it },
            Turn.PLAYER,
            manaSpent)
    }

    /**
     * Determines whether it is possible to cast the given spell.
     */
    private fun canCast(spell: Spell): Boolean = spell.cost <= player.manaPoints + rechargeBonus() &&
                (activeEffects[spell.effect] == null || activeEffects[spell.effect] == 1)

    private fun cast(spell: Spell): GameState {
        val updatedPlayerHitPoints = player.hitPoints + spell.healing
        val updatedPlayerMana = player.manaPoints + rechargeBonus() - spell.cost
        val updatedBossHitPoints = boss.hitPoints - poisonDamage() - spell.damage

        val updatedEffects = activeEffects.mapValues { it.value - 1 }.filterValues { 0 < it }.toMutableMap()
        if (spell.effect != null) {
            updatedEffects[spell.effect] = spell.effect.duration
        }

        return GameState(Boss(updatedBossHitPoints, boss.damage),
            Player(updatedPlayerHitPoints, updatedPlayerMana),
            updatedEffects,
            Turn.BOSS,
            manaSpent + spell.cost)
    }

    /**
     * Determines poison damage to apply to the boss this turn.
     */
    private fun poisonDamage() = when {
        activeEffects.keys.contains(Effect.POISON) -> POISON_DAMAGE
        else -> 0
    }

    /**
     * Determines the mana bonus points to award to the player this turn.
     */
    private fun rechargeBonus() = when {
        activeEffects.keys.contains(Effect.RECHARGE) -> RECHARGE_BONUS
        else -> 0
    }

    /**
     * Determines the amount of armor for the player this turn.
     */
    private fun getPlayerArmor() = when {
        activeEffects.keys.contains(Effect.SHIELD) -> SHIELD_BONUS
        else -> 0
    }
}

