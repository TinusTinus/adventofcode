package nl.mvdr.adventofcode.adventofcode2015.day22

enum class Effect(val duration: Int) {
    /** Armor is increased by 7. */
    SHIELD(6),
    /** At the start of each turn, deals the boss 3 damage. */
    POISON(6),
    /** At the start of each turn, gives 101 new mana. */
    RECHARGE(5)
}