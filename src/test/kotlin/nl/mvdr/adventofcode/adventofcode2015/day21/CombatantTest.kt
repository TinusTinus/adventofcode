package nl.mvdr.adventofcode.adventofcode2015.day21

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class CombatantTest {
    companion object {
        @JvmStatic
        fun testTurnsToKill() : List<Arguments> = listOf(
            Arguments.of(
                Player(8, 5, 5),
                Boss(12, 7, 2),
                4),
            Arguments.of(
                Boss(12, 7, 2),
                Player(8, 5, 5),
                4),
            Arguments.of(
                Player(8, Weapon.SHORTSWORD, Armor.PLATEMAIL, setOf()),
                Boss(12, 7, 2),
                4),
            Arguments.of(
                Player(8, Weapon.DAGGER, Armor.CHAINMAIL, setOf(Ring.DAMAGE_PLUS_ONE, Ring.DEFENSE_PLUS_THREE)),
                Boss(12, 7, 2),
                4)
        )
    }

    @ParameterizedTest
    @MethodSource
    fun testTurnsToKill(attacker: Combatant, defender: Combatant, expectedTurns: Int) {
        val result = attacker.turnsToKill(defender)
        assertEquals(expectedTurns, result)
    }
}