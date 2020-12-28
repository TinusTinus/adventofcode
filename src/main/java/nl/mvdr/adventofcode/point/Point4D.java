package nl.mvdr.adventofcode.point;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A point in four dimensions (spacetime).
 * 
 * Not to be confused with {@link Point} or {@link Point3D},
 * which represent points in two or three dimensions, respectively.
 *
 * @author Martijn van de Rijdt
 */
public record Point4D(int x, int y, int z, int w) {

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
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry">Manhattan distance</a> between this point and another.
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
    
    /** @return the 80 other points where any of their coordinates differ by at most 1 from this one */
    public Set<Point4D> neighbours() {
        Set<Point4D> result = new HashSet<>();
        for (int xx = x - 1; xx != x + 2; xx++) {
            for (int yy = y - 1; yy != y + 2; yy++) {
                for (int zz = z - 1; zz != z + 2; zz++) {
                    for (int ww = w - 1; ww != w + 2; ww++) {
                        Point4D point = new Point4D(xx, yy, zz, ww);
                        if (!point.equals(this)) {
                            result.add(point);
                        }
                    }
                }
            }
        }
        return Set.copyOf(result);
    }
    
    @Override
    public String toString() {
        return x + "," + y + "," + z + "," + w;
    }
}
