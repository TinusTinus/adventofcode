package nl.mvdr.adventofcode.adventofcode2015.day21

data class Boss(override val hitPoints: Int, override val damage: Int, override val armor: Int) : Combatant { }

fun parseBoss(lines: List<String>): Boss {
    val (hitPoints, damage, armor) = lines.map { it.filter(Character::isDigit).toInt() }
    return Boss(hitPoints, damage, armor)
}
