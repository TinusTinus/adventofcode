package nl.mvdr.adventofcode.adventofcode2018.day11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link Cell}.
 *
 * @author Martijn van de Rijdt
 */
public class CellTest {
    
    /**
     * Performs a test case for {@link Cell#powerLevel}.
     * 
     * @param x cell's x coordinate
     * @param y cell's y coordinate
     * @param serialNumber grid's serial number
     * @param expectedPowerLevel expected power level
     */
    private void testPowerLevel(int x, int y, int serialNumber, int expectedPowerLevel) {
        Cell cell = new Cell(x, y, serialNumber);
        
        int powerLevel = cell.getPowerLevel();
        
        Assertions.assertEquals(expectedPowerLevel, powerLevel);
    }
    
    /**
     * Test case for {@link Cell#powerLevel()} based on the first example from the puzzle description.
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
        testPowerLevel(3, 5, 8, 4);
    }
    
    /**
     * Test case for {@link Cell#powerLevel()} based on an example from the puzzle description.
     * 
     * "Fuel cell at  122,79, grid serial number 57: power level -5."
     */
    @Test
    public void testPowerLevelExample1() {
        testPowerLevel(122, 79, 57, -5);
    }
    
    /**
     * Test case for {@link Cell#powerLevel()} based on an example from the puzzle description.
     * 
     * "Fuel cell at 217,196, grid serial number 39: power level 0."
     */
    @Test
    public void testPowerLevelExample2() {
        testPowerLevel(217, 196, 39, 0);
    }
    
    /**
     * Test case for {@link Cell#powerLevel()} based on an example from the puzzle description.
     * 
     * "Fuel cell at 101,153, grid serial number 71: power level 4."
     */
    @Test
    public void testPowerLevelExample3() {
        testPowerLevel(101, 153, 71, 4);
    }
    
    /**
     * Test case for {@link Cell#squareTotalPowerLevel()} based on an example from the puzzle description.
     * 
     * "For grid serial number 18, the largest total 3x3 square has a top-left corner of 33,45 (with a total power of 29)".
     */
    @Test
    public void testSquareTotalPowerLevelExample0() {
        Cell cell = new Cell(33, 45, 18);
        
        int result = cell.squareTotalPowerLevel();
        
        Assertions.assertEquals(29, result);
    }
    
    /**
     * Test case for {@link Cell#squareTotalPowerLevel()} based on an example from the puzzle description.
     * 
     * "For grid serial number 42, the largest 3x3 square's top-left is 21,61 (with a total power of 30)".
     */
    @Test
    public void testSquareTotalPowerLevelExample1() {
        Cell cell = new Cell(21, 61, 42);
        
        int result = cell.squareTotalPowerLevel();
        
        Assertions.assertEquals(30, result);
    }
    
    /**
     * Test case for {@link Cell#squareTotalPowerLevel(int)} based on an example from the puzzle description.
     * 
     * "For grid serial number 18, the largest total square (with a total power of 113) is 16x16 and has a top-left corner of 90,269, so its identifier is 90,269,16."
     */
    @Test
    public void testSquareTotalPowerLevelAnySizeExample0() {
        Cell cell = new Cell(90, 269, 18, 16);
        
        int result = cell.squareTotalPowerLevel();
        
        Assertions.assertEquals(113, result);
    }
    
    /**
     * Test case for {@link Cell#squareTotalPowerLevel(int)} based on an example from the puzzle description.
     * 
     * "For grid serial number 42, the largest total square (with a total power of 119) is 12x12 and has a top-left corner of 232,251, so its identifier is 232,251,12."
     */
    @Test
    public void testSquareTotalPowerLevelAnySizeExample1() {
        Cell cell = new Cell(232, 251, 42, 12);
        
        int result = cell.squareTotalPowerLevel();
        
        Assertions.assertEquals(119, result);
    }
}
