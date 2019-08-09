package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.util.Set;

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
        
        Group group = Group.parseGroup(Army.IMMUNE_SYSTEM, 1, input);
        
        Assertions.assertEquals(Army.IMMUNE_SYSTEM, group.getArmy());
        Assertions.assertEquals(1, group.getId());
        Assertions.assertEquals(303, group.getUnits());
        Assertions.assertEquals(10428, group.getHitPoints());
        Assertions.assertEquals(328, group.getAttackDamage());
        Assertions.assertEquals("radiation", group.getAttackType());
        Assertions.assertEquals(13, group.getInitiative());
        Assertions.assertTrue(group.getWeaknesses().isEmpty());
        Assertions.assertTrue(group.getImmunities().isEmpty());
    }
    
    /** Test case for {@link Group#parse(String)}. */
    @Test
    public void testParse() {
        String input = "18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10";
        
        Group group = Group.parseGroup(Army.IMMUNE_SYSTEM, 1, input);
        
        Assertions.assertEquals(Army.IMMUNE_SYSTEM, group.getArmy());
        Assertions.assertEquals(1, group.getId());
        Assertions.assertEquals(18, group.getUnits());
        Assertions.assertEquals(729, group.getHitPoints());
        Assertions.assertEquals(Set.of("fire"), group.getWeaknesses());
        Assertions.assertEquals(Set.of("cold", "slashing"), group.getImmunities());
        Assertions.assertEquals(8, group.getAttackDamage());
        Assertions.assertEquals("radiation", group.getAttackType());
        Assertions.assertEquals(10, group.getInitiative());
    }
    
    /** Test case for {@link Group#parse(String)}. */
    @Test
    public void testParseImmunitiesFirst() {
        String input = "18 units each with 729 hit points (immune to cold, slashing; weak to fire) with an attack that does 8 radiation damage at initiative 10";
        
        Group group = Group.parseGroup(Army.IMMUNE_SYSTEM, 1, input);
        
        Assertions.assertEquals(Army.IMMUNE_SYSTEM, group.getArmy());
        Assertions.assertEquals(1, group.getId());
        Assertions.assertEquals(18, group.getUnits());
        Assertions.assertEquals(729, group.getHitPoints());
        Assertions.assertEquals(Set.of("cold", "slashing"), group.getImmunities());
        Assertions.assertEquals(Set.of("fire"), group.getWeaknesses());
        Assertions.assertEquals(8, group.getAttackDamage());
        Assertions.assertEquals("radiation", group.getAttackType());
        Assertions.assertEquals(10, group.getInitiative());
    }
    
    /** Test case for {@link Group#parse(String)}. */
    @Test
    public void testParseImmunitiesOnly() {
        String input = "18 units each with 729 hit points (immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10";
        
        Group group = Group.parseGroup(Army.IMMUNE_SYSTEM, 1, input);
        
        Assertions.assertEquals(Army.IMMUNE_SYSTEM, group.getArmy());
        Assertions.assertEquals(1, group.getId());
        Assertions.assertEquals(18, group.getUnits());
        Assertions.assertEquals(729, group.getHitPoints());
        Assertions.assertEquals(Set.of("cold", "slashing"), group.getImmunities());
        Assertions.assertTrue(group.getWeaknesses().isEmpty());
        Assertions.assertEquals(8, group.getAttackDamage());
        Assertions.assertEquals("radiation", group.getAttackType());
        Assertions.assertEquals(10, group.getInitiative());
    }
    
    /** Test case for {@link Group#parse(String)}. */
    @Test
    public void testParseWeaknessesOnly() {
        String input = "18 units each with 729 hit points (weak to fire, tickling) with an attack that does 8 radiation damage at initiative 10";
        
        Group group = Group.parseGroup(Army.IMMUNE_SYSTEM, 1, input);
        
        Assertions.assertEquals(Army.IMMUNE_SYSTEM, group.getArmy());
        Assertions.assertEquals(1, group.getId());
        Assertions.assertEquals(18, group.getUnits());
        Assertions.assertEquals(729, group.getHitPoints());
        Assertions.assertEquals(Set.of("fire", "tickling"), group.getWeaknesses());
        Assertions.assertTrue(group.getImmunities().isEmpty());
        Assertions.assertEquals(8, group.getAttackDamage());
        Assertions.assertEquals("radiation", group.getAttackType());
        Assertions.assertEquals(10, group.getInitiative());
    }
    
    /** Test case for {@link Group#toString()}, for a group without any damage immunities and weaknsesses. */
    @Test
    public void testToStringNoWeaknessesNoImmunities() {
        String input = "303 units each with 10428 hit points with an attack that does 328 radiation damage at initiative 13";
        Group group = Group.parseGroup(Army.IMMUNE_SYSTEM, 1, input);
        
        String result = group.toString();
        
        Assertions.assertEquals("Immune System group 1: 303 units each with 10428 hit points with an attack that does 328 radiation damage at initiative 13", result);
    }
    
    /** Test case for {@link Group#toString()}, for a group without any damage immunities and weaknsesses. */
    @Test
    public void testToString() {
        String input = "18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10";
        Group group = Group.parseGroup(Army.INFECTION, 3, input);
        
        String result = group.toString();
        
        Assertions.assertEquals("Infection group 3: 18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10", result);
    }
    
    /** Test case for {@link Group#effectivePower()}. */
    @Test
    public void testEffectivePower() {
        String input = "18 units each with 729 hit points (weak to fire; immune to cold, slashing) with an attack that does 8 radiation damage at initiative 10";
        Group group = Group.parseGroup(Army.IMMUNE_SYSTEM, 1, input);
        
        int result = group.effectivePower();
        
        Assertions.assertEquals(144, result);
    }
}
