package nl.mvdr.adventofcode.point;

import java.util.function.BiFunction;
import java.util.function.ToIntFunction;

/**
 * An axis in three dimensions.
 *
 * @author Martijn van de Rijdt
 */
public enum Axis3D {
    X(Point3D::x, (start, distance) -> new Point3D(start.x() + distance.intValue(), start.y(), start.z())),
    Y(Point3D::y, (start, distance) -> new Point3D(start.x(), start.y() + distance.intValue(), start.z())),
    Z(Point3D::z, (start, distance) -> new Point3D(start.x(), start.y(), start.z() + distance.intValue()));
    
    private final ToIntFunction<Point3D> getFunction;
    private final BiFunction<Point3D, Integer, Point3D> movementFunction;
    
    Axis3D(ToIntFunction<Point3D> getFunction, BiFunction<Point3D, Integer, Point3D> movementFunction) {
        this.getFunction = getFunction;
        this.movementFunction = movementFunction;
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
        return movementFunction.apply(start, Integer.valueOf(distance));
    }
}
