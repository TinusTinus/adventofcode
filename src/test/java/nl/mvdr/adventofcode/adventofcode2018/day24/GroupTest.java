package nl.mvdr.adventofcode.adventofcode2018.day24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Group}.
 *
 * @author Martijn van de Rijdt
 */
public class GroupTest {
    /** Test case for {@link Group#parse(String)}, for a group without any damage immunities and weaknsesses. */
    @Test
    public void testParseNoWeaknessesNoImmunities() {
        String input = "303 units each with 10428 hit points with an attack that does 328 radiation damage at initiative 13";
        
        Group group = Group.parse(input);
        
        Assertions.assertEquals(303, group.getUnits());
        Assertions.assertEquals(10428, group.getHitPoints());
        Assertions.assertEquals(328, group.getAttackDamage());
        Assertions.assertEquals("radiation", group.getAttackType());
        Assertions.assertEquals(13, group.getInitiative());
        Assertions.assertTrue(group.getWeaknesses().isEmpty());
        Assertions.assertTrue(group.getImmunities().isEmpty());
    }
    
    /** Test case for {@link Group#toString()}, for a group without any damage immunities and weaknsesses. */
    @Test
    public void testToStringNoWeaknessesNoImmunities() {
        String input = "303 units each with 10428 hit points with an attack that does 328 radiation damage at initiative 13";
        Group group = Group.parse(input);
        
        String result = group.toString();
        
        Assertions.assertEquals(input, result);
    }
}
