package nl.mvdr.adventofcode.adventofcode2016.day08;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the screen.
 *
 * @author Martijn van de Rijdt
 */
class Screen {
    
    private final int width;
    private final int height;
    
    private Set<Point> lit;

    /**
     * Constructor for a blank screen.
     * 
     * @param width width of the screen in pixels
     * @param height height of the screen in pixels
     */
    Screen(int width, int height) {
        this(width, height, Set.of());
    }
    
    /**
     * Constructor.
     * 
     * @param width width of the screen in pixels
     * @param height height of the screen in pixels
     * @param lit pixels that are lit
     */
    private Screen(int width, int height, Set<Point> lit) {
        super();
        this.width = width;
        this.height = height;
        this.lit = lit;
    }
    
    /**
     * Turns on all of the pixels in a rectangle at the top-left of the screen which is {@code a} wide and {@code b} tall.
     * 
     * @param a width of the rectangle
     * @param b height of the rectangle
     * @return new screen
     */
    Screen rect(int a, int b) {
        Set<Point> newLit = new HashSet<>(lit);
        for (int x = 0; x != a; x++) {
            for (int y = 0; y != b; y++) {
                newLit.add(new Point(x, y));
            }
        }
        
        return new Screen(width, height, Collections.unmodifiableSet(newLit));
    }
    
    /**
     * Shifts all of the pixels in row {@code a} (0 is the top row) right by {@code b} pixels.
     * 
     * Pixels that would fall off the right end appear at the left end of the row.
     * 
     * @param a row identifier
     * @param b offset
     * @return new screen
     */
    Screen rotateRow(int a, int b) {
        Set<Point> newLit = lit.stream()
                .map(point -> {
                    Point result;
                    if (point.y() == a) {
                        // Rotate right
                        result = new Point((point.x() + b) % width, a);
                    } else {
                        // Different row, leave unchanged
                        result = point;
                    }
                    return result;
                })
                .collect(Collectors.toSet());
        return new Screen(width, height, Collections.unmodifiableSet(newLit));
    }
    
    /**
     * Shifts all of the pixels in column {@code a} (0 is the left column) down by {@code b} pixels.
     * 
     * Pixels that would fall off the bottom appear at the top of the column.
     * 
     * @param a column identifier
     * @param b offset
     * @return new screen
     */
    Screen rotateColumn(int a, int b) {
        Set<Point> newLit = lit.stream()
                .map(point -> {
                    Point result;
                    if (point.x() == a) {
                        // Rotate down
                        result = new Point(a, (point.y() + b) % height);
                    } else {
                        // Different column, leave unchanged
                        result = point;
                    }
                    return result;
                })
                .collect(Collectors.toSet());
        return new Screen(width, height, Collections.unmodifiableSet(newLit));
    }
    
    /** @return the number of pixels which are lit */
    int litPixels() {
        return lit.size();
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Screen:\n");
        
        for (int y = 0; y != height; y++) {
            for (int x = 0; x != width; x++) {
                if (lit.contains(new Point(x, y))) {
                    result.append("#");
                } else {
                    result.append(".");
                }
            }
            result.append("\n");
        }
        
        return result.toString();
    }
}
