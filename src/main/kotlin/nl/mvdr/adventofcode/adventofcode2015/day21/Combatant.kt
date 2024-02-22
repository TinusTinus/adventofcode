package nl.mvdr.adventofcode.adventofcode2015.day21

import kotlin.math.ceil

interface Combatant {
    val hitPoints: Int
    val damage: Int
    val armor: Int

    /** Calculates how many turns it would take for this combatant to kill the given [opponent]. */
    fun turnsToKill(opponent: Combatant): Int = when {
        damage <= opponent.armor -> Int.MAX_VALUE // effectively infinity: the opponent will never take damage
        else -> ceil(opponent.hitPoints.toDouble() / (damage - opponent.armor).toDouble()).toInt()
    }
}

