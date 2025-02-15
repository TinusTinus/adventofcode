package nl.mvdr.adventofcode.adventofcode2015.day22

data class Boss(val hitPoints: Int, val damage: Int)

fun parseBoss(lines: Sequence<String>): Boss {
    val (hitPoints, damage) = lines.toList().map { it.filter(Character::isDigit).toInt() }
    return Boss(hitPoints, damage)
}
