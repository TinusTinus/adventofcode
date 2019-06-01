package nl.mvdr.adventofcode.adventofcode2018.day11;

/**
 * A fuel cell.
 *
 * @author Martijn van de Rijdt
 */
class Cell {

    private final int powerLevel;

    /**
     * Computes the power level of a cell.
     * 
     * The power level in a given fuel cell can be found through the following process:
     * <ul>
     * <li>Find the fuel cell's rack ID, which is its X coordinate plus 10.</li>
     * <li>Begin with a power level of the rack ID times the Y coordinate.</li>
     * <li>Increase the power level by the value of the grid serial number (your puzzle input).</li>
     * <li>Set the power level to itself multiplied by the rack ID.</li>
     * <li>Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).</li>
     * <li>Subtract 5 from the power level.</li>
     * </ul>
     * 
     * @return this cell's power level
     */
    private static int calculatePowerLevel(int x, int y, int serialNumber) {
        // Find the fuel cell's rack ID, which is its X coordinate plus 10.
        int rackId = x + 10;
        
        // Begin with a power level of the rack ID times the Y coordinate.
        int result = rackId * y;
        // Increase the power level by the value of the grid serial number (your puzzle input).
        result = result + serialNumber;
        // Set the power level to itself multiplied by the rack ID.
        result = result * rackId;
        // Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).
        result = result / 100;
        result = result % 10;
        // Subtract 5 from the power level.
        result = result - 5;
        
        return result;
    }
    
    /**
     * Constructor, for a 3x3 square.
     * 
     * @param x x coordinate (horizontal)
     * @param y y coordinate (vertical)
     * @param serialNumber the grid serial number
     */
    Cell(int x, int y, int serialNumber) {
        super();
        this.powerLevel = calculatePowerLevel(x, y, serialNumber);
    }
    
    /** @return power level of this cell */
    int getPowerLevel() {
        return powerLevel;
    }
}
