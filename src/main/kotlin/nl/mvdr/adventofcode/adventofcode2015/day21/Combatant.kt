package nl.mvdr.adventofcode.adventofcode2015.day21

import kotlin.math.ceil

fun parseBoss(lines: List<String>): Boss {
    val hitPoints = parseStat(lines[0], "Hit Points: ")
    val damage = parseStat(lines[1], "Damage: ")
    val armor = parseStat(lines[2], "Armor: ")
    return Boss(hitPoints, damage, armor)
}

private fun parseStat(text: String, expectedPrefix: String): Int {
    if (!text.startsWith(expectedPrefix)) {
        throw IllegalArgumentException("Input '$text' is expected to start with prefix '$expectedPrefix'")
    }
    return text.filter(Character::isDigit).toInt()
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