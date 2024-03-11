package nl.mvdr.adventofcode.adventofcode2015.day22

enum class Spell(val cost: Int, val damage: Int, val healing: Int, val effect: Effect?) {
    /** Instantly does 4 damage. */
    MAGIC_MISSILE(53, 4, 0, null),
    /** Instantly does 2 damage and heals for 2 hit points. */
    DRAIN(73, 2, 2, null),
    /** Starts an effect that lasts for 6 turns. While it is active, armor is increased by 7. */
    SHIELD(113, 0, 0, Effect.SHIELD),
    /** Starts an effect that lasts for 6 turns. At the start of each turn while it is active, it deals the boss 3 damage. */
    POISON(173, 0, 0, Effect.POISON),
    /** Starts an effect that lasts for 5 turns. At the start of each turn while it is active, it gives 101 new mana. */
    RECHARGE(229, 0, 0, Effect.RECHARGE)
}