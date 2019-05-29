package nl.mvdr.adventofcode.adventofcode2018.day06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A point in two dimensions.
 *
 * @author Martijn van de Rijdt
 * 
 */
class Point {

    private final int x;

    private final int y;

    /**
     * Constructor.
     * 
     * @param x x coordinate (horizontal)
     * @param y y coordinate (vertical)
     */
    Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /** @return x coordinate (horizontal) */
    int getX() {
        return x;
    }
    
    /** @return y coordinate (vertical) */
    int getY() {
        return y;
    }
    
    /**
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry"> Manhattan distance</a> between this point and another.
     * 
     * @param other other point
     * @return Manhattan distance
     */
    int manhattanDistance(Point other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    /**
     * Parses the input.
     * 
     * @param path path to the text file containing string representations of points
     * @return set of points
     * @throws IOException if the input file cannot be read
     */
    static Set<Point> parse(Path path) throws IOException {
        return Files.lines(path)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // split the integer coordinates
                .map(line -> line.split(", "))
                .map(array -> new Point(Integer.valueOf(array[0]), Integer.valueOf(array[1])))
                .collect(Collectors.toSet());
                        
    }
}
