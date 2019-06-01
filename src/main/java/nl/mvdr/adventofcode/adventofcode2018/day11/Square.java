package nl.mvdr.adventofcode.adventofcode2018.day11;

import java.util.stream.IntStream;

/**
 * Representation of a square of cells.
 *
 * @author Martijn van de Rijdt
 */
class Square {
    private final int[][] grid;
    
    private final int x;
    
    private final int y;
    
    private final int size;
    
    private final boolean printSize;
    
    /**
     * Constructor.
     * 
     * @param grid the complete power grid
     * @param x x coordinate of the left of this square
     * @param y y coordinate of the top of this square
     * @param size size of this square
     * @param printSize whether the size should be included in {@link #toString()}
     */
    Square(int[][] grid, int x, int y, int size, boolean printSize) {
        super();
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.size = size;
        this.printSize = printSize;
    }
    
    /** @return total power level of this square */
    int totalPowerLevel() {
        return IntStream.range(0, size)
                .flatMap(dx -> IntStream.range(0, size).map(dy -> grid[x + dx][y + dy]))
                .sum();
    }
    
    @Override
    public String toString() {
        String result = x + "," + y;
        if (printSize) {
            result = result + "," + size;
        }
        return result;
    }
}
