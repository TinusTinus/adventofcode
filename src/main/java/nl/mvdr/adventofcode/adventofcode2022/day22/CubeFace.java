package nl.mvdr.adventofcode.adventofcode2022.day22;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a single two-dimensional square face of a three-dimensional cube.
 * 
 * Note that, in the context of this class, there are two separate ways to reason about coordinates:
 * <li>absolute coordinates: coordinates with regard to the map from the puzzle input;</li>
 * <li>relative coordinates: coordinates with regard to this particular square, where the top-left corner is to be seen as the origin (0, 0).</li>
 * 
 * @param topLeft absolute coordinates of the top-left-most point of this face
 * @param size length and width of the square
 *
 * @author Martijn van de Rijdt
 */
record CubeFace(Point topLeft, int size) {

    /**
     * Checks whether the given point lies within this face.
     * 
     * @param absolutePoint point in absolute coordinates
     * @return whether the given point lies within this square
     */
    boolean containsAbsolute(Point absolutePoint) {
        return containsRelative(toRelative(absolutePoint));
    }
    
    /**
     * Checks whether the given point lies within this face.
     * 
     * @param relativePoint point in absolute coordinates
     * @return whether the given point lies within this square
     */
    boolean containsRelative(Point relativePoint) {
        return 0 <= relativePoint.x() && relativePoint.x() < size
                && 0 <= relativePoint.y() && relativePoint.y() < size;
    }
    
    /**
     * Converts a point from absolute coordinates to relative coordinates.
     * 
     * @param absolute point in absolute coordinates
     * @return the same point in relative coordinates
     */
    private Point toRelative(Point absolute) {
        return absolute.subtract(topLeft);
    }
    
    /**
     * Moves a single step off this face, onto another face of the cube.
     * 
     * Take care: this method returns a point in <em>relative</em> coordinates!
     * 
     * @param startingPosition starting point and facing, where the point coordinates are absolute coordinates
     * @param newFacing the new facing on the next face
     * @return relative coordinates on the other face
     */
    Point wrapAround(Position startingPosition, Direction newFacing) {
        Point startingPointRelative = toRelative(startingPosition.location());
        return switch(startingPosition.facing()) {
            case LEFT  -> wrapAroundLeft(startingPointRelative, newFacing);
            case RIGHT -> wrapAroundRight(startingPointRelative, newFacing);
            case UP    -> wrapAroundUp(startingPointRelative, newFacing);
            case DOWN  -> wrapAroundDown(startingPointRelative, newFacing);
            default    -> throw new IllegalArgumentException("Unexpected facing: " + startingPosition.facing());
        };
    }

    /**
     * Moves a single step off this face over the left edge, onto another face of the cube.
     * 
     * @param startingPointRelative starting point, where the point coordinates are relative coordinates
     * @param newFacing the new facing on the next face
     * @return relative coordinates on the other face
     */
    private Point wrapAroundLeft(Point startingPointRelative, Direction newFacing) {
        if (startingPointRelative.x() != 0) {
            throw new IllegalArgumentException("Starting position is not at the left edge: " + startingPointRelative);
        }
        return switch(newFacing) {
            case LEFT  -> new Point(size - 1, startingPointRelative.y());
            case DOWN  -> new Point(startingPointRelative.y(), 0);
            case UP    -> new Point(size - 1 - startingPointRelative.y(), size - 1);
            case RIGHT -> new Point(0, size - 1 - startingPointRelative.y());
            default    -> throw new IllegalArgumentException("Unexpected facing: " + newFacing);
        };
    }
    
    /**
     * Moves a single step off this face over the right edge, onto another face of the cube.
     * 
     * @param startingPointRelative starting point, where the point coordinates are relative coordinates
     * @param newFacing the new facing on the next face
     * @return relative coordinates on the other face
     */
    private Point wrapAroundRight(Point startingPointRelative, Direction newFacing) {
        if (startingPointRelative.x() != size - 1) {
            throw new IllegalArgumentException("Starting position is not at the right edge: " + startingPointRelative);
        }
        return Point.ORIGIN; // TODO
    }
    
    /**
     * Moves a single step off this face over the bottom edge, onto another face of the cube.
     * 
     * @param startingPointRelative starting point, where the point coordinates are relative coordinates
     * @param newFacing the new facing on the next face
     * @return relative coordinates on the other face
     */
    private Point wrapAroundDown(Point startingPointRelative, Direction newFacing) {
        if (startingPointRelative.y() != size - 1) {
            throw new IllegalArgumentException("Starting position is not at the bottom edge: " + startingPointRelative);
        }
        return Point.ORIGIN; // TODO
    }
    
    /**
     * Moves a single step off this face over the top edge, onto another face of the cube.
     * 
     * @param startingPointRelative starting point, where the point coordinates are relative coordinates
     * @param newFacing the new facing on the next face
     * @return relative coordinates on the other face
     */
    private Point wrapAroundUp(Point startingPointRelative, Direction newFacing) {
        if (startingPointRelative.y() != 0) {
            throw new IllegalArgumentException("Starting position is not at the top edge: " + startingPointRelative);
        }
        return Point.ORIGIN; // TODO
    }
}
