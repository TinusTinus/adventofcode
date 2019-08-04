package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.util.Objects;
import java.util.Set;

import javax.annotation.processing.Generated;

/**
 * A point in three dimensions.
 * 
 * Not to be confused with {@link nl.mvdr.adventofcode.adventofcode2018.point.Point},
 * which represents a point in two dimensions.
 *
 * @author Martijn van de Rijdt
 */
class Point {

    private final int x;

    private final int y;
    
    private final int z;

    /**
     * Constructor.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    Point(int x, int y, int z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /** @return x coordinate */
    int getX() {
        return x;
    }
    
    /** @return y coordinate */
    int getY() {
        return y;
    }
    
    /** @return z coordinate */
    int getZ() {
        return z;
    }
    
    /**
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry"> Manhattan distance</a> between this point and another.
     * 
     * @param other other point
     * @return Manhattan distance
     */
    int manhattanDistance(Point other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z);
    }
    
    @Override
    public String toString() {
        return "<" + x + "," + y + "," + z + ">";
    }
    
    /**
     * Parses a string containing a set of coordinates, for example: "<1,3,1>".
     * 
     * @param text text to be parsed
     * @return point
     */
    public static Point parse(String text) {
        String[] coordinateStrings = text.substring(1, text.length() - 1).split(",");
        return new Point(
                Integer.parseInt(coordinateStrings[0]),
                Integer.parseInt(coordinateStrings[1]),
                Integer.parseInt(coordinateStrings[2]));
    }
    
    /** @return the neighbouring points to this one */
    public Set<Point> neighbours() {
        return Set.of(
            new Point(x - 1, y, z),
            new Point(x + 1, y, z),
            new Point(x, y - 1, z),
            new Point(x, y + 1, z),
            new Point(x, y, z - 1),
            new Point(x, y, z + 1)
        );
    }

    @Override
    @Generated("Eclipse")
    public int hashCode() {
        return Objects.hash(Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z));
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
        return x == other.x && y == other.y && z == other.z;
    }
    
    
}
