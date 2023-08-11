package nl.mvdr.adventofcode.adventofcode2022.day18;

import nl.mvdr.adventofcode.point.Axis3D;
import nl.mvdr.adventofcode.point.Point3D;

/**
 * Sides of a cube, named after the numbers that would be printed on a six-sided die.
 *
 * @author Martijn van de Rijdt
 */
enum Side {
    
    SIDE_1(Axis3D.Z, -1),
    SIDE_2(Axis3D.Y, 1),
    SIDE_3(Axis3D.X, 1),
    SIDE_4(Axis3D.X, -1),
    SIDE_5(Axis3D.Y, -1),
    SIDE_6(Axis3D.Z, 1);
    
    /** The axis in three-dimensional space on which this side resides. */
    private Axis3D axis;
    
    /** Indicates the direction along the axis: -1 or 1. */
    private int offset;

    /**
     * Constructor.
     * 
     * @param axis the axis in three dimensions: x, y or z
     * @param offset the direction along the axis: -1 or 1
     */
    Side(Axis3D axis, int offset) {
        this.axis = axis;
        this.offset = offset;
    }
    
    /**
     * Returns the given source cube's neighbouring cube on this side.
     * 
     * @param source source cube
     * @return neighbour
     */
    Point3D neighbour(Point3D source) {
        return axis.move(source, offset);
    }
}
