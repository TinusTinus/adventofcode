package nl.mvdr.adventofcode.adventofcode2015.day21

import kotlin.math.ceil

interface Combatant {
    val hitPoints: Int
    val damage: Int
    val armor: Int

    /** Calculates how many turns it would take for this combatant to kill the given [opponent]. */
    fun turnsToKill(opponent: Combatant): Int =
        ceil(opponent.hitPoints.toDouble() / damagePerTurn(opponent).toDouble()).toInt()

    /**
     * Determines the amount of damage this combatant does to the given [opponent] per turn.
     */
    private fun damagePerTurn(opponent: Combatant) = (damage - opponent.armor).coerceAtLeast(1)
}

