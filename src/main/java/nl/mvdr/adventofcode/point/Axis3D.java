package nl.mvdr.adventofcode.point;

import java.util.function.ToIntFunction;

/**
 * An axis in three dimensions.
 *
 * @author Martijn van de Rijdt
 */
public enum Axis3D {
    X(Point3D::x),
    Y(Point3D::y),
    Z(Point3D::z);
    
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
}
