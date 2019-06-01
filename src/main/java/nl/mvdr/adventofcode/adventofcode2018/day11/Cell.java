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

    /**
     * Constructor.
     * 
     * @param x x coordinate (horizontal)
     * @param y y coordinate (vertical)
     * @param serialNumber the grid serial number
     */
    Cell(int x, int y, int serialNumber) {
        super();
        this.x = x;
        this.y = y;
        this.serialNumber = serialNumber;
        this.squareSize = 3;
        this.includeSquareSizeInToString = false;
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
        super();
        this.x = x;
        this.y = y;
        this.serialNumber = serialNumber;
        this.squareSize = squareSize;
        this.includeSquareSizeInToString = true;
    }
    
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
    int powerLevel() {
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
     * Computes the total power level of a square of power cells, of which this cell is the top left one.
     * 
     * @return total power level
     */
    int squareTotalPowerLevel() {
        return IntStream.range(0, squareSize)
                .mapToObj(Integer::valueOf)
                .flatMap(x -> IntStream.range(0, squareSize).mapToObj(y -> new Cell(this.x + x, this.y + y, this.serialNumber)))
                .mapToInt(Cell::powerLevel)
                .sum();
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
