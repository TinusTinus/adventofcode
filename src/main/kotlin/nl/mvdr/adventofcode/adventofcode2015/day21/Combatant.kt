package nl.mvdr.adventofcode.adventofcode2015.day21

import kotlin.math.ceil

fun parseBoss(lines: List<String>): Boss {
    val (hitPoints, damage, armor) = lines.map { it.filter(Character::isDigit).toInt() }
    return Boss(hitPoints, damage, armor)
}

/** Returns all possible equipment combinations a player can use. */
fun getAllPossiblePlayers(): Set<Player> {
    val result = mutableSetOf<Player>()
    for (weapon in Weapon.entries) {
        for (armor in Armor.entries union setOf(null)) {
            result.add(Player(weapon = weapon, armor = armor, rings = setOf()))
            for (firstRing in Ring.entries) {
                result.add(Player(weapon = weapon, armor = armor, rings = setOf(firstRing)))
                for (secondRing in Ring.entries.filter { firstRing.ordinal < it.ordinal }) {
                    result.add(Player(weapon = weapon, armor = armor, rings = setOf(firstRing, secondRing)))
                }
            }
        }
    }
    return result
}

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

data class Boss(override val hitPoints: Int, override val damage: Int, override val armor: Int) : Combatant { }

data class Player(override val hitPoints: Int, override val damage: Int, override val armor: Int, val equipmentCost: Int = 0): Combatant {
    private constructor(hitPoints: Int, equipment: Set<Equipment>):
            this(hitPoints, equipment.sumOf(Equipment::damage), equipment.sumOf(Equipment::armor), equipment.sumOf(Equipment::cost))

    constructor(hitPoints: Int = 100, weapon: Weapon, armor: Armor?, rings: Set<Ring>) :
            this(hitPoints, setOfNotNull(weapon, armor) union rings) {
        if (2 < rings.size) {
            throw IllegalArgumentException("Too many rings: $rings")
        }
    }
}