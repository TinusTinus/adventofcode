package nl.mvdr.adventofcode.adventofcode2015.day21

data class Player(override val hitPoints: Int, override val damage: Int, override val armor: Int, val equipmentCost: Int = 0): Combatant {
    private constructor(hitPoints: Int, equipment: Set<EquipmentPiece>):
            this(hitPoints, equipment.sumOf(EquipmentPiece::damage), equipment.sumOf(EquipmentPiece::armor), equipment.sumOf(EquipmentPiece::cost))

    constructor(hitPoints: Int = 100, weapon: Weapon, armor: Armor?, rings: Set<Ring>) :
            this(hitPoints, setOfNotNull(weapon, armor) union rings) {
        if (2 < rings.size) {
            throw IllegalArgumentException("Too many rings: $rings")
        }
    }
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
