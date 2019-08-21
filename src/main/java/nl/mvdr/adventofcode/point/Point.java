package nl.mvdr.adventofcode.point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.processing.Generated;

/**
 * A point in two dimensions.
 *
 * @author Martijn van de Rijdt
 */
public class Point implements Comparable<Point> {

    private final int x;

    private final int y;

    /**
     * Constructor.
     * 
     * @param x x coordinate (horizontal)
     * @param y y coordinate (vertical)
     */
    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /** @return x coordinate (horizontal) */
    public int getX() {
        return x;
    }
    
    /** @return y coordinate (vertical) */
    public int getY() {
        return y;
    }
    
    /**
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry">Manhattan distance</a> between this point and another.
     * 
     * @param other other point
     * @return Manhattan distance
     */
    public int manhattanDistance(Point other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }
    
    /**
     * Computes the total distance to the given set of points.
     * 
     * @param points set of points points
     * @return total distance
     */
    public int totalManhattanDistance(Set<Point> points) {
        return points.stream()
                .mapToInt(this::manhattanDistance)
                .sum();
    }
    
    /** @return the four neighbouring points to this one */
    public Set<Point> neighbours() {
        return Set.of(
            leftNeighbour(),
            rightNeighbour(),
            aboveNeighbour(),
            belowNeighbour()
        );
    }
    
    /** @return the eight neighbouring points to this one, including diagonals */
    public Set<Point> neighboursIncludingDiagonals() {
        return Set.of(
            leftNeighbour(),
            rightNeighbour(),
            aboveNeighbour(),
            belowNeighbour(),
            new Point(x - 1, y - 1),
            new Point(x - 1, y + 1),
            new Point(x + 1, y - 1),
            new Point(x + 1, y + 1)
        );
    }

    /** @return neighbour to the left (assuming x coordinates are numbererd left-to-right) */
    public Point leftNeighbour() {
        return new Point(x - 1, y);
    }

    /** @return neighbour to the right (assuming x coordinates are numbered left-to-right) */
    public Point rightNeighbour() {
        return new Point(x + 1, y);
    }

    /** @return above neighbour (assuming y coordinates are numbered top-down) */
    public Point aboveNeighbour() {
        return new Point(x, y - 1);
    }

    /** @return below neighbour (assuming y coordinates are numbered top-down) */
    public Point belowNeighbour() {
        return new Point(x, y + 1);
    }
    
    // Convenience methods for use in maps
    /** @return neighbour to the West */
    public Point westNeighbour() {
        return leftNeighbour();
    }

    /** @return neighbour to the East */
    public Point eastNeighbour() {
        return rightNeighbour();
    }

    /** @return neighbour to the North */
    public Point northNeighbour() {
        return aboveNeighbour();
    }

    /** @return neighbour to the South */
    public Point southNeighbour() {
        return belowNeighbour();
    }
    
    /**
     * Translates this point with the given coordinates.
     * 
     * @param other x and y coordinates to be added
     * @return translated point
     */
    public Point translate(Point other) {
        return new Point(this.x + other.x, this.y + other.y);
    }
    
    @Override
    public String toString() {
        return x + "," + y;
    }
    
    @Override
    @Generated("Eclipse")
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    @Generated("Eclipse")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        return x == other.x && y == other.y;
    }
    
    /**
     * {@inheritDoc}
     * <p>
     * Compares the two points based on the <em>reading order</em>: top-to-bottom, then left-to-right 
     * (assuming x coordinates are numbered left-to-right and y coordinates are numbered top-down).
     */
    @Override
    public int compareTo(Point other) {
        Comparator<Point> readingOrder = Comparator.comparing(Point::getY).thenComparing(Point::getX);
        return readingOrder.compare(this, other);
    }
    
    /**
     * Parses a text file containing string representations of points, for example: "98, 231".
     * 
     * @param path path to the text file 
     * @return set of points
     * @throws IOException if the input file cannot be read
     */
    public static Set<Point> parse(Path path) throws IOException {
        return Files.lines(path)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // split the integer coordinates
                .map(line -> line.split(", "))
                .map(array -> new Point(Integer.parseInt(array[0]), Integer.parseInt(array[1])))
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a text file containing string representations of ranges of points, for example: "x=438, y=595..605" or "y=1230, x=524..528".
     * 
     * @param path path to the text file 
     * @return set of points
     * @throws IOException if the input file cannot be read
     */
    public static Set<Point> parseRanges(Path path) throws IOException {
        return Files.lines(path)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line
                .map(Point::parseRange)
                // collect the result
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Parses a line of text containing a string representation of a range of points, for example: "x=438, y=595..605" or "y=1230, x=524..528".
     * 
     * @param line text to be parsed
     * @return set of points
     */
    static Set<Point> parseRange(String line) {
        String[] parts = line.split(", ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse: " + line);
        }
        
        String xString;
        String yString;
        if (parts[0].startsWith("x=") && parts[1].startsWith("y=")) {
            xString = parts[0];
            yString = parts[1];
        } else if (parts[0].startsWith("y=") && parts[1].startsWith("x=")) {
            xString = parts[1];
            yString = parts[0];
        } else {
            throw new IllegalArgumentException("Unable to parse: " + line);
        }
        
        Set<Integer> xValues = parseCoordinateRange(xString.substring(2));
        Set<Integer> yValues = parseCoordinateRange(yString.substring(2));
        
        return xValues.stream()
                .flatMap(x -> yValues.stream().map(y -> new Point(x, y)))
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a range for a single coordinate, for example: "48" (meaning just the value 48) or "23..27" (meaning the values 23, 24, 25, 26, 27).
     * 
     * @param line text to be parsed
     * @return coordinates within the range
     */
    private static Set<Integer> parseCoordinateRange(String input) {
        String[] parts = input.split("\\.\\.");
        if (parts.length == 0) {
            throw new IllegalArgumentException("Unable to parse: " + input);
        }
        
        int minimum = Integer.parseInt(parts[0]);
        int maximum;
        if (parts.length == 1) {
            maximum = minimum;
        } else if (parts.length == 2){
            maximum = Integer.parseInt(parts[1]);
        } else {
            throw new IllegalArgumentException("Unable to parse: " + input);
        }
        
        return IntStream.rangeClosed(minimum, maximum)
                .boxed()
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a string containing a comma-separated pair of coordinates, for example: "32,43".
     * 
     * @param text text to be parsed
     * @return point
     */
    public static Point parse(String text) {
        String[] coordinateStrings = text.split(",");
        return new Point(Integer.parseInt(coordinateStrings[0]), Integer.parseInt(coordinateStrings[1]));
    }
}
