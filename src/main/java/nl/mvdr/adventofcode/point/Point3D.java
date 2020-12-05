package nl.mvdr.adventofcode.point;

import java.util.Set;

/**
 * A point (/vector) in three dimensions.
 * 
 * Not to be confused with {@link Point}, which represents a point in two dimensions.
 * 
 * Can be used to represent a three-dimensional location, but also a velocity or acceleration.
 *
 * @author Martijn van de Rijdt
 */
public record Point3D(int x, int y, int z) {

    /**
     * Parses a string containing a set of coordinates, for example: "<1,3,1>".
     * 
     * @param text text to be parsed
     * @return point
     */
    public static Point3D parse(String text) {
        String[] coordinateStrings = text.substring(1, text.length() - 1).split(",");
        return new Point3D(
                Integer.parseInt(coordinateStrings[0]),
                Integer.parseInt(coordinateStrings[1]),
                Integer.parseInt(coordinateStrings[2]));
    }
    
    /**
     * Constructor.
     * 
     * @param point x and y coordinates
     * @param z z coordinate
     */
    public Point3D(Point point, int z) {
        this(point.x(), point.y(), z);
    }

    /**
     * Gets the value along the given axis.
     * 
     * @param axis axis
     * @return value
     */
    public int getValue(Axis3D axis) {
        return axis.getValue(this);
    }
    
    /**
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry">Manhattan distance</a> between this point and another.
     * 
     * @param other other point
     * @return Manhattan distance
     */
    public int manhattanDistance(Point3D other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z);
    }
    
    /**
     * Computes the <a href="https://en.wikipedia.org/wiki/Taxicab_geometry">Manhattan distance</a> between this point and <0,0,0>.
     * 
     * @return Manhattan distance
     */
    public int manhattanDistanceToOrigin() {
        return Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z);
    }
    
    /** @return the neighbouring points to this one */
    public Set<Point3D> neighbours() {
        return offsetOnAxes(1);
    }
    
    /**
     * Calculates the (normally 6) points that are offset removed from this one, on each axis.
     * 
     * @param offset offset
     * @return points
     */
    public Set<Point3D> offsetOnAxes(int offset) {
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
    
    /**
     * Adds the given vector to this vector.
     * 
     * @param other other vector
     * @return new vector, which is equal to the sum of the given vectors
     */
    public Point3D add(Point3D other) {
        return new Point3D(this.x() + other.x(), this.y() + other.y(), this.z() + other.z());
    }
    
    /**
     * Subtracts the given vector to this vector.
     * 
     * @param other other vector
     * @return new vector
     */
    public Point3D subtract(Point3D other) {
        return new Point3D(this.x() - other.x(), this.y() - other.y(), this.z() - other.z());
    }
    
    /** @return new vector, which is the inverse of this one */
    public Point3D negate() {
        return new Point3D(-x, -y, -z);
    }
    
    /** @return signum vector */
    public Point3D signum() {
        return new Point3D(Integer.signum(x), Integer.signum(y), Integer.signum(z));
    }
    
    @Override
    public String toString() {
        return "<" + x + "," + y + "," + z + ">";
    }
}
