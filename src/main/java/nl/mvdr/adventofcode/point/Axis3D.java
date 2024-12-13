package nl.mvdr.adventofcode.point;

import java.util.function.ToIntFunction;

/**
 * An axis in three dimensions.
 *
 * @author Martijn van de Rijdt
 */
public enum Axis3D {
    X(Orientation.HORIZONTAL, Point3D::x) {
        @Override
        public Point3D updateValue(Point3D point, int newValue) {
            return new Point3D(newValue, point.y(), point.z());
        }
    },
    Y(Orientation.HORIZONTAL, Point3D::y) {
        @Override
        public Point3D updateValue(Point3D point, int newValue) {
            return new Point3D(point.x(), newValue, point.z());
        }
    },
    Z(Orientation.VERTICAL, Point3D::z) {
        @Override
        public Point3D updateValue(Point3D point, int newValue) {
            return new Point3D(point.x(), point.y(), newValue);
        }
    };
    
    private final ToIntFunction<Point3D> getFunction;
    private final Orientation orientation;
    
    Axis3D(Orientation orientation, ToIntFunction<Point3D> getFunction) {
        this.orientation = orientation;
        this.getFunction = getFunction;
    }
    
    /**
     * Gets the given point's value on this axis.
     * 
     * @param point point in three dimensions
     * @return x, y or z value
     */
    int getValue(Point3D point) {
        return getFunction.applyAsInt(point);
    }
    
    /**
     * Moves the given distance along this axis.
     * 
     * @param start starting point
     * @param distance distance to move
     * @return new position
     */
    public Point3D move(Point3D start, int distance) {
        return updateValue(start, getValue(start) + distance);
    }
    
    /**
     * Updates the coordinate on this axis.
     * 
     * @param point point to update
     * @param newValue new coordinate value
     * @return updated point
     */
    public abstract Point3D updateValue(Point3D point, int newValue);
    
    /**
     * @return orientation of this axis in three-dimensional space (where the z axis is considered the only vertical one)
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Parses the string representation of an axis.
     * @param stringRepresentation the string representation: "x", "y" or "z"
     * @return axis
     */
    public static Axis3D parse(String stringRepresentation) {
        return switch (stringRepresentation) {
            case "x" -> X;
            case "y" -> Y;
            case "z" -> Z;
            default -> throw new IllegalArgumentException("Unexpected string representation of an axis: " + stringRepresentation);
        };
    }
}
