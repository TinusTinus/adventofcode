package nl.mvdr.adventofcode.adventofcode2015.day21

interface Combatant {
    val hitPoints: Int
    val damage: Int
    val armor: Int
}

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

class Boss(override val hitPoints: Int, override val damage: Int, override val armor: Int) : Combatant { }

class Player(override val hitPoints: Int, override val damage: Int, override val armor: Int): Combatant {
    private constructor(hitPoints: Int, equipment: Set<Equipment>):
            this(hitPoints, equipment.sumOf(Equipment::damage), equipment.sumOf(Equipment::armor))

    constructor(hitPoints: Int = 100, weapon: Weapon, armor: Armor?, rings: Set<Ring>) :
            this(hitPoints, setOfNotNull(weapon, armor) union rings) {
        if (2 < rings.size) {
            throw IllegalArgumentException("Too many rings: $rings")
        }
    }
}