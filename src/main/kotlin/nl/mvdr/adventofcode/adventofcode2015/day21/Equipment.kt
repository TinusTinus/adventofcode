package nl.mvdr.adventofcode.adventofcode2015.day21

interface EquipmentPiece {
    val cost: Int
    val damage: Int
    val armor: Int
}

enum class Weapon(override val cost: Int, override val damage: Int): EquipmentPiece {
    DAGGER(8, 4),
    SHORTSWORD(10, 5),
    WARHAMMER(25, 6),
    LONGSWORD(40, 7),
    GREATAXE(74, 8);

    override val armor: Int get() = 0
}

enum class Armor(override val cost: Int, override val armor: Int): EquipmentPiece {
    LEATHER(13, 1),
    CHAINMAIL(31, 2),
    SPLINTMAIN(53, 3),
    BANDEDMAIL(75, 4),
    PLATEMAIL(102, 5);

    override val damage: Int get() = 0
}

enum class Ring(override val cost: Int, override val damage: Int, override val armor: Int): EquipmentPiece {
    DAMAGE_PLUS_ONE(25, 1, 0),
    DAMAGE_PLUS_TWO(50, 2, 0),
    DAMAGE_PLUS_THREE(100, 3, 0),
    DEFENSE_PLUS_ONE(20, 0, 1),
    DEFENSE_PLUS_TWO(40, 0, 2),
    DEFENSE_PLUS_THREE(80, 0, 3)
}