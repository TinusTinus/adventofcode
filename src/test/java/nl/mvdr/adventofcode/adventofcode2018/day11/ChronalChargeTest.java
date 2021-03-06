package nl.mvdr.adventofcode.adventofcode2018.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link ChronalCharge}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalChargeTest {
    /**
     * Test case for {@link ChronalCharge#calculatePowerLevel(int, int, int)} based on the first example from the puzzle description.
     * 
     * "For example, to find the power level of the fuel cell at 3,5 in a grid with serial number 8:
     * <ul>
     * <li>The rack ID is 3 + 10 = 13.</li>
     * <li>The power level starts at 13 * 5 = 65.</li>
     * <li>Adding the serial number produces 65 + 8 = 73.</li>
     * <li>Multiplying by the rack ID produces 73 * 13 = 949.</li>
     * <li>The hundreds digit of 949 is 9.</li>
     * <li>Subtracting 5 produces 9 - 5 = 4.</li>
     * </ul>
     * So, the power level of this fuel cell is 4."
     */
    @Test
    public void testPowerLevelExample0() {
        int powerLevel = ChronalCharge.calculatePowerLevel(3, 5, 8);
        
        Assertions.assertEquals(4, powerLevel);
    }
    
    /**
     * Test case for {@link ChronalCharge#calculatePowerLevel(int, int, int)} based on an example from the puzzle description.
     * 
     * "Fuel cell at  122,79, grid serial number 57: power level -5."
     */
    @Test
    public void testPowerLevelExample1() {
        int powerLevel = ChronalCharge.calculatePowerLevel(122, 79, 57);
        
        Assertions.assertEquals(-5, powerLevel);
    }
    
    /**
     * Test case for {@link ChronalCharge#calculatePowerLevel(int, int, int)} based on an example from the puzzle description.
     * 
     * "Fuel cell at 217,196, grid serial number 39: power level 0."
     */
    @Test
    public void testPowerLevelExample2() {
        int powerLevel = ChronalCharge.calculatePowerLevel(217, 196, 39);
        
        Assertions.assertEquals(0, powerLevel);
    }
    
    /**
     * Test case for {@link ChronalCharge#calculatePowerLevel(int, int, int)} based on an example from the puzzle description.
     * 
     * "Fuel cell at 101,153, grid serial number 71: power level 4."
     */
    @Test
    public void testPowerLevelExample3() {
        int powerLevel = ChronalCharge.calculatePowerLevel(101, 153, 71);
        
        Assertions.assertEquals(4, powerLevel);
    }
}
