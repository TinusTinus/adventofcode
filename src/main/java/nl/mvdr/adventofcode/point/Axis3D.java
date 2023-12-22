package nl.mvdr.adventofcode.point;

import java.util.function.ToIntFunction;

/**
 * An axis in three dimensions.
 *
 * @author Martijn van de Rijdt
 */
public enum Axis3D {
    X(Point3D::x) {
        @Override
        public Point3D updateValue(Point3D point, int newValue) {
            return new Point3D(newValue, point.y(), point.z());
        }
    },
    Y(Point3D::y) {
        @Override
        public Point3D updateValue(Point3D point, int newValue) {
            return new Point3D(point.x(), newValue, point.z());
        }
    },
    Z(Point3D::z) {
        @Override
        public Point3D updateValue(Point3D point, int newValue) {
            return new Point3D(point.x(), point.y(), newValue);
        }
    };
    
    private final ToIntFunction<Point3D> getFunction;
    
    Axis3D(ToIntFunction<Point3D> getFunction) {
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
}
