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
class Point3D {

    private final int x;

    private final int y;
    
    private final int z;

    /**
     * Parses a string containing a set of coordinates, for example: "<1,3,1>".
     * 
     * @param text text to be parsed
     * @return point
     */
    static Point3D parse(String text) {
        String[] coordinateStrings = text.substring(1, text.length() - 1).split(",");
        return new Point3D(
                Integer.parseInt(coordinateStrings[0]),
                Integer.parseInt(coordinateStrings[1]),
                Integer.parseInt(coordinateStrings[2]));
    }
    
    /**
     * Constructor.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    Point3D(int x, int y, int z) {
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
    int manhattanDistance(Point3D other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z);
    }
    
    /** @return the neighbouring points to this one */
    Set<Point3D> neighbours() {
        return offsetOnAxes(1);
    }
    
    /**
     * Calculates the (normally 6) points that are offset removed from this one, on each axis.
     * 
     * @param offset offset
     * @return points
     */
    Set<Point3D> offsetOnAxes(int offset) {
        Set<Point3D> result;
        if (offset == 0) {
            result = Set.of(this);
        } else {
            result = Set.of(
                    new Point3D(x - offset, y, z),
                    new Point3D(x + offset, y, z),
                    new Point3D(x, y - offset, z),
                    new Point3D(x, y + offset, z),
                    new Point3D(x, y, z - offset),
                    new Point3D(x, y, z + offset)
                );
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "<" + x + "," + y + "," + z + ">";
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
        Point3D other = (Point3D) obj;
        return x == other.x && y == other.y && z == other.z;
    }
}
