package nl.mvdr.adventofcode.adventofcode2015.day21

interface Equipment {
    val cost: Int
    val damage: Int
    val armor: Int
}

enum class Weapon(override val cost: Int, override val damage: Int, override val armor: Int): Equipment {
    DAGGER(8, 4, 0),
    SHORTSWORD(10, 5, 0),
    WARHAMMER(25, 6, 0),
    LONGSWORD(40, 7, 0),
    GREATAXE(74, 8, 0)
}

enum class Armor(override val cost: Int, override val damage: Int, override val armor: Int): Equipment {
    LEATHER(13, 0, 1),
    CHAINMAIL(31, 0, 2),
    SPLINTMAIN(53, 0, 3),
    BANDEDMAIL(75, 0, 4),
    PLATEMAIL(102, 0, 5)
}

enum class Ring(override val cost: Int, override val damage: Int, override val armor: Int): Equipment {
    DAMAGE_PLUS_ONE(25, 1, 0),
    DAMAGE_PLUS_TWO(50, 2, 0),
    DAMAGE_PLUS_THREE(100, 3, 0),
    DEFENSE_PLUS_ONE(20, 0, 1),
    DEFENSE_PLUS_TWO(40, 0, 2),
    DEFENSE_PLUS_THREE(80, 0, 3)
}