package nl.mvdr.adventofcode.point;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A point in two dimensions.
 *
 * @author Martijn van de Rijdt
 */
public record Point(int x, int y) implements Comparable<Point> {
    
    /** 0, 0 location */
    public static final Point ORIGIN = new Point(0, 0);
    
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
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry">Manhattan distance</a> between this point and <0, 0>.
     * 
     * @param other other point
     * @return Manhattan distance
     */
    public int manhattanDistanceToOrigin() {
        return Math.abs(x) + Math.abs(y);
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
    
    // Convenience methods for use in geographical maps
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
    
    /**
     * Multiplies both coordinates with the given number.
     * 
     * @param i number to multiply by
     * @return new point
     */
    public Point times(int i) {
        return new Point(x * i, y * i);
    }

    /**
     * Computes the angle, in degrees, of the line from this point to the other point.
     * 
     * @param other other point
     * @return angle in degrees; 0 &le; angle &lt; 360
     */
    public double computeAngle(Point other) {
        double angle = Math.toDegrees(Math.atan2(y - other.y, other.x - x));
        
        while (angle < 0) {
            angle = angle + 360;
        }
        
        return angle;
    }
    
    @Override
    public String toString() {
        return x + "," + y;
    }
    
    /**
     * {@inheritDoc}
     * <p>
     * Compares the two points based on the <em>reading order</em>: top-to-bottom, then left-to-right 
     * (assuming x coordinates are numbered left-to-right and y coordinates are numbered top-down).
     */
    @Override
    public int compareTo(Point other) {
        Comparator<Point> readingOrder = Comparator.comparing(Point::y).thenComparing(Point::x);
        return readingOrder.compare(this, other);
    }
    
    /**
     * Parses a text file containing string representations of points, for example: "98, 231".
     * 
     * @param lines lines from the text file
     * @return set of points
     */
    public static Set<Point> parse(Stream<String> lines) {
        return lines
                // filter out empty lines
                .filter(Predicate.not(String::isBlank))
                // split the integer coordinates
                .map(line -> line.split(", "))
                .map(array -> new Point(Integer.parseInt(array[0]), Integer.parseInt(array[1])))
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a text file containing string representations of ranges of points, for example: "x=438, y=595..605" or "y=1230, x=524..528".
     * 
     * @param lines contents of the input file 
     * @return set of points
     */
    public static Set<Point> parseRanges(Stream<String> lines) {
        return lines
                // ignore empty lines (the last line in the file)
                .filter(Predicate.not(String::isBlank))
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
                .flatMap(x -> yValues.stream().map(y -> new Point(x.intValue(), y.intValue())))
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
    
    /**
     * Checks whether the given three points are all on the same line.
     * 
     * @param point1 first point
     * @param point2 second point
     * @param point3 third point
     * @return whether the three points are all on the same line
     */
    public static boolean sameLine(Point point1, Point point2, Point point3) {
        int x1 = point1.x();
        int y1 = point1.y();
        int x2 = point2.x();
        int y2 = point2.y();
        int x3 = point3.x();
        int y3 = point3.y();
        
        return (x2 - x1) * (y3 - y1) == (y2 - y1) * (x3 - x1);
    }
    
    /**
     * Computes one of the bounding coordinates of a set of points.
     * 
     * @param points set of points
     * @return minimum X coordinate
     */
    public static int minX(Set<Point> points) {
        return points.stream()
                .mapToInt(Point::x)
                .min()
                .orElse(0);
    }
    
    /**
     * Computes one of the bounding coordinates of a set of points.
     * 
     * @param points set of points
     * @return maximum X coordinate
     */
    public static int maxX(Set<Point> points) {
        return points.stream()
                .mapToInt(Point::x)
                .max()
                .orElse(0);
    }
    
    /**
     * Computes one of the bounding coordinates of a set of points.
     * 
     * @param points set of points
     * @return minimum Y coordinate
     */
    public static int minY(Set<Point> points) {
        return points.stream()
                .mapToInt(Point::y)
                .min()
                .orElse(0);
    }
    
    /**
     * Computes one of the bounding coordinates of a set of points.
     * 
     * @param points set of points
     * @return maximum Y coordinate
     */
    public static int maxY(Set<Point> points) {
        return points.stream()
                .mapToInt(Point::y)
                .max()
                .orElse(0);
    }
}
