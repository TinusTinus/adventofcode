package nl.mvdr.adventofcode.point;

import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.processing.Generated;

/**
 * A point in four dimensions (spacetime).
 * 
 * Not to be confused with {@link nl.mvdr.adventofcode.point.Point},
 * which represents a point in two dimensions.
 *
 * @author Martijn van de Rijdt
 */
public class Point4D {

    private final int x;
    private final int y;
    private final int z;
    private final int w;

    /**
     * Parses a text file containing string representations of points.
     * 
     * @param lines contents of the input text file
     * @return set of points
     */
    public static Set<Point4D> parse(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isBlank))
                .map(Point4D::parsePoint)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a string containing a set of coordinates, for example: "1,3,1,-6".
     * 
     * @param text text to be parsed
     * @return point
     */
    private static Point4D parsePoint(String text) {
        String[] coordinateStrings = text.split(",");
        return new Point4D(
                Integer.parseInt(coordinateStrings[0]),
                Integer.parseInt(coordinateStrings[1]),
                Integer.parseInt(coordinateStrings[2]),
                Integer.parseInt(coordinateStrings[3]));
    }
    
    /**
     * Constructor.
     * 
     * @param coordinate0 first coordinate
     * @param coordinate1 second coordinate
     * @param coordinate2 third coordinate
     * @param coordinate3 dourth coordinate
     */
    private Point4D(int coordinate0, int coordinate1, int coordinate2, int coordinate3) {
        super();
        this.x = coordinate0;
        this.y = coordinate1;
        this.z = coordinate2;
        this.w = coordinate3;
    }

    /**
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry"> Manhattan distance</a> between this point and another.
     * 
     * @param other other point
     * @return Manhattan distance
     */
    public int manhattanDistance(Point4D other) {
        return Math.abs(this.x - other.x)
                + Math.abs(this.y - other.y)
                + Math.abs(this.z - other.z)
                + Math.abs(this.w - other.w);
    }
    
    /**
     * Determines whether this point is close enough to the given constellation to
     * be a part of it.
     * 
     * Two points are in the same constellation if their manhattan distance apart is
     * no more than 3 or if they can form a chain of points, each a manhattan
     * distance no more than 3 from the last, between the two of them. (That is, if
     * a point is close enough to a constellation, it "joins" that constellation.)
     * 
     * @param constellation constellation, represented as a (nonempty) set of points
     * @return whether this point is in range
     */
    public boolean inRange(Set<Point4D> constellation) {
        return constellation.stream()
                .mapToInt(this::manhattanDistance)
                .anyMatch(i -> i <= 3);
    }
    
    @Override
    public String toString() {
        return x + "," + y + "," + z + "," + w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z), Integer.valueOf(w));
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
        Point4D other = (Point4D) obj;
        return x == other.x && y == other.y && z == other.z
                && w == other.w;
    }
}
