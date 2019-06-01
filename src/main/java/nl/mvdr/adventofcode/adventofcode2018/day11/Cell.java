package nl.mvdr.adventofcode.adventofcode2018.day11;

import java.util.stream.IntStream;

/**
 * A fuel cell.
 *
 * @author Martijn van de Rijdt
 */
class Cell {

    private final int x;

    private final int y;
    
    private final int serialNumber;
    
    private final int squareSize;
    
    private final boolean includeSquareSizeInToString;
    
    private final int powerLevel;

    /**
     * Computes this cell's power level.
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
        this(x, y, serialNumber, 3, false);
    }
    
    /**
     * Constructor.
     * 
     * @param x x coordinate (horizontal)
     * @param y y coordinate (vertical)
     * @param serialNumber the grid serial number
     * @param squareSize size of the square, of which this cell is the upper left corner, for total power calculation
     */
    Cell(int x, int y, int serialNumber, int squareSize) {
        this(x, y, serialNumber, squareSize, true);
    }
    
    /**
     * Constructor.
     * 
     * @param x x coordinate (horizontal)
     * @param y y coordinate (vertical)
     * @param serialNumber the grid serial number
     * @param squareSize size of the square, of which this cell is the upper left corner, for total power calculation
     * @param includeSquareSizeInToString whether the square size should be included in the output of {@link #toString()}
     */
    private Cell(int x, int y, int serialNumber, int squareSize, boolean includeSquareSizeInToString) {
        super();
        this.x = x;
        this.y = y;
        this.serialNumber = serialNumber;
        this.squareSize = squareSize;
        this.includeSquareSizeInToString = includeSquareSizeInToString;
        this.powerLevel = calculatePowerLevel(x, y, serialNumber);
    }
    
    /**
     * Computes the total power level of a square of power cells, of which this cell is the top left one.
     * 
     * @return total power level
     */
    int squareTotalPowerLevel() {
        return IntStream.range(0, squareSize)
                .mapToObj(Integer::valueOf)
                .flatMap(x -> IntStream.range(0, squareSize).mapToObj(y -> new Cell(this.x + x, this.y + y, this.serialNumber)))
                .mapToInt(Cell::getPowerLevel)
                .sum();
    }

    /** @return power level of this cell */
    int getPowerLevel() {
        return powerLevel;
    }
    
    @Override
    public String toString() {
        String result = x + "," + y;
        if (includeSquareSizeInToString) {
            result = result + ", " + squareSize;
        }
        return result;
    }
}
