package nl.mvdr.adventofcode.point;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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
    
    /**
     * Computes the set of points at exactly the given distance from this point.
     * 
     * @param manhattenDistance distance
     * @return stream of points
     */
    public Stream<Point> pointsAtManhattanDistance(int distance) {
        Stream<Point> result;
        if (distance < 0) {
            throw new IllegalArgumentException("Distance may not be negative but was: " + distance);
        } else if (distance == 0) {
            result = Stream.of(this);
        } else {
            result = IntStream.range(0, distance + 1)
                    .mapToObj(i -> Stream.of(
                            new Point(x + i, y + distance - i),
                            new Point(x + i, y - distance + i),
                            new Point(x - i, y + distance - i),
                            new Point(x - i, y - distance + i)))
                    .flatMap(Function.identity())
                    .distinct();
        }
        return result;
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
    
    /** @return set containing this point, as well as its four neighbouring points */
    public Set<Point> andNeighbours() {
        return Set.of(
            this,
            leftNeighbour(),
            rightNeighbour(),
            aboveNeighbour(),
            belowNeighbour()
        );
    }

    /** @return neighbour to the left (assuming x coordinates are numbererd left-to-right) */
    public Point leftNeighbour() {
        return Direction.LEFT.move(this);
    }

    /** @return neighbour to the right (assuming x coordinates are numbered left-to-right) */
    public Point rightNeighbour() {
        return Direction.RIGHT.move(this);
    }

    /** @return above neighbour (assuming y coordinates are numbered top-down) */
    public Point aboveNeighbour() {
        return Direction.UP.move(this);
    }

    /** @return below neighbour (assuming y coordinates are numbered top-down) */
    public Point belowNeighbour() {
        return Direction.DOWN.move(this);
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
    
    /** @return neighbour to the Northwest */
    public Point northwestNeighbour() {
        return northNeighbour().westNeighbour();
    }
    
    /** @return neighbour to the Northeast */
    public Point northeastNeighbour() {
        return northNeighbour().eastNeighbour();
    }
    
    /** @return neighbour to the Southwest */
    public Point southwestNeighbour() {
        return southNeighbour().westNeighbour();
    }
    
    /** @return neighbour to the Southeast */
    public Point southeastNeighbour() {
        return southNeighbour().eastNeighbour();
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
    
    /**
     * Returns a set containing all points at the given distance of this point.
     * 
     * @param maxDistance Manhattan distance (see {@link #manhattanDistance(Point)})
     * @return points within the given range
     */
    public Set<Point> pointsAtDistance(int maxDistance, int minX, int maxX, int minY, int maxY) {
        if (maxDistance < 0) {
            throw new IllegalArgumentException("Distance must be non-negative but was: " + maxDistance);
        }
        Set<Point> visited = new HashSet<>();
        visited.add(this);
        
        var d = 0;
        Set<Point> pointsAtDistance = Set.of(this);
        while (d < maxDistance) {
            pointsAtDistance = pointsAtDistance.stream()
                    .map(Point::neighbours)
                    .flatMap(Set::stream)
                    .filter(position -> minX <= position.x())
                    .filter(position -> position.x() <= maxX)
                    .filter(position -> minY <= position.y())
                    .filter(position -> position.y() <= maxY)
                    .filter(Predicate.not(visited::contains))
                    .collect(Collectors.toSet());
            visited.addAll(pointsAtDistance);
            d++;
        }
        
        return pointsAtDistance;
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
     * Adds two points together, as if they were two-dimensional vectors.
     * 
     * @param other other point
     * @return sum of the two points
     */
    public Point add(Point other) {
        return new Point(this.x + other.x, this.y + other.y);
    }
    
    /**
     * Negates both coordinates of this point.
     * 
     * @return negated point
     */
    public Point negate() {
        return new Point(-x, -y);
    }
    
    /**
     * Subtracts the given other point from this one, as if they were two-dimensional vectors.
     * 
     * @param other other point
     * @return result of subtracting the given point from this one
     */
    public Point subtract(Point other) {
        return add(other.negate());
    }
    
    /**
     * @return this point with its x and y coordinates swapped
     */
    public Point transpose() {
        return new Point(y, x);
    }
    
    /**
     * Applies {@link Math#floorMod(int, int)} to both coordinates.
     * 
     * @param xDivisor divisor for the x coordinate
     * @param yDivisor divisor for the y coordinate
     * @return result
     */
    public Point floorMod(int xDivisor, int yDivisor) {
        return new Point(Math.floorMod(x, xDivisor),
                Math.floorMod(y, yDivisor));
    }
    
    /**
     * Applies {@link Math#floorDiv(int, int)} to both coordinates.
     * 
     * @param xDivisor divisor for the x coordinate
     * @param yDivisor divisor for the y coordinate
     * @return result
     */
    public Point floorDiv(int xDivisor, int yDivisor) {
        return new Point(Math.floorDiv(x, xDivisor),
                Math.floorDiv(y, yDivisor));
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
    
    /**
     * Returns a stream of the points in the given range.
     * 
     * @param minX minimum X value
     * @param maxX maximum X value
     * @param minY minimum Y value
     * @param maxY maximum Y value
     * @return points in range
     */
    public static Stream<Point> points(int minX, int maxX, int minY, int maxY) {
        return IntStream.range(minX, maxX + 1)
                .boxed()
                .flatMap(x -> IntStream.range(minY, maxY + 1)
                        .boxed()
                        .map(y -> new Point(x.intValue(), y.intValue())));
    }
    
    /**
     * Helper method to create a visual two-dimensional string representation of a set of points.
     * 
     * As in many puzzle inputs, empty spaces are represented by '.' and elements of the given set by '#'.
     * 
     * @param points points to visualize
     * @return (potentially multiline!) string
     */
    public static String visualize(Set<Point> points) {
        int minX = Point.minX(points);
        int maxX = Point.maxX(points);
        int minY = Point.minY(points);
        int maxY = Point.maxY(points);

        return visualize(points, minX, maxX, minY, maxY);
    }

    /**
     * Helper method to create a visual two-dimensional string representation of a set of points.
     * 
     * As in many puzzle inputs, empty spaces are represented by '.' and elements of the given set by '#'.
     * 
     * @param points points to visualize
     * @param minX minimum x coordinate value
     * @param maxX maximum x coordinate value
     * @param minY minimum y coordinate value
     * @param maxY maximum y coordinate value
     * @return
     */
    public static String visualize(Set<Point> points, int minX, int maxX, int minY, int maxY) {
        StringBuilder builder = new StringBuilder();
        for (int y = minY; y != maxY + 1; y++) {
            for (int x = minX; x != maxX + 1; x++) {
                if (points.contains(new Point(x, y))) {
                    builder.append("#");
                } else {
                    builder.append(".");
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
    
    /**
     * Helper method to parse puzzle input representing a two-dimensional map.
     * 
     * @param <T> value type in the resulting map
     * @param lines lines, where each line represents a row in a two-dimensional map
     * @param characterMapper how to map each character to an actual value
     * @return map
     */
    public static final <T> Map<Point, T> parse2DMap(List<String> lines, CharacterMapper<T> characterMapper) {
        Map<Point, T> result = new HashMap<>();
        Point.parse2DMap(lines, (point, character) -> {
            T value = characterMapper.map(character);
            if (value != null) {
                result.put(point, value);
            }
        });
        return result;
    }
    
    /**
     * Helper method to parse puzzle input representing a two-dimensional map.
     * 
     * @param lines lines, where each line represents a row in a two-dimensional map
     * @param characterConsumer how to handle each character
     */
    public static final void parse2DMap(List<String> lines, CharacterConsumer characterConsumer) {
        for (var y = 0; y != lines.size(); y++) {
            var line = lines.get(y);
            for (var x = 0; x != line.length(); x++) {
                characterConsumer.accept(new Point(x, y), line.charAt(x));
            }
        }
    }
}
