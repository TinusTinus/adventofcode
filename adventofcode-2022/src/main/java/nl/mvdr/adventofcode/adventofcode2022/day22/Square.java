package nl.mvdr.adventofcode.adventofcode2022.day22;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a single two-dimensional square, which makes up one sixth of the map in the puzzle input.
 * 
 * Can be interpreted as one side of a three-dimensional cube (in part two of the puzzle).
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
record Square(Point topLeft, int size) {

    /**
     * Checks whether the given point lies within this square.
     * 
     * @param absolutePoint point in absolute coordinates
     * @return whether the given point lies within this square
     */
    boolean contains(Point absolutePoint) {
        return containsRelative(toRelative(absolutePoint));
    }
    
    /**
     * Checks whether the given point lies within this square.
     * 
     * @param relativePoint point in absolute coordinates
     * @return whether the given point lies within this square
     */
    private boolean containsRelative(Point relativePoint) {
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
     * Converts a point from relative coordinates to absolute coordinates.
     * 
     * @param relative point in relative coordinates
     * @return the same point in absolute coordinates
     */
    Point toAbsolute(Point relative) {
        return topLeft.add(relative);
    }
    
    /**
     * Moves a single step off this square, onto another square.
     * 
     * Take care: this method returns a point in <em>relative</em> coordinates!
     * 
     * @param startingPosition starting point and facing, where the point coordinates are absolute coordinates
     * @param newFacing the new facing on the next square
     * @return relative coordinates on the other square
     */
    Point wrapAround(Position startingPosition, Direction newFacing) {
        Point startingPointRelative = toRelative(startingPosition.location());
        return switch(startingPosition.facing()) {
            case LEFT  -> wrapAroundLeft(startingPointRelative, newFacing);
            case RIGHT -> wrapAroundRight(startingPointRelative, newFacing);
            case UP    -> wrapAroundUp(startingPointRelative, newFacing);
            case DOWN  -> wrapAroundDown(startingPointRelative, newFacing);
            case DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT -> throw new IllegalArgumentException("Unexpected diagonal direction: " + newFacing);
            default    -> throw new IllegalArgumentException("Unexpected facing: " + startingPosition.facing());
        };
    }

    /**
     * Moves a single step off this face over the left edge, onto another square.
     * 
     * @param startingPointRelative starting point, where the point coordinates are relative coordinates
     * @param newFacing the new facing on the next square
     * @return relative coordinates on the other square
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
            case DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT -> throw new IllegalArgumentException("Unexpected diagonal direction: " + newFacing);
            default    -> throw new IllegalArgumentException("Unexpected facing: " + newFacing);
        };
    }
    
    /**
     * Moves a single step off this face over the right edge, onto another square.
     * 
     * @param startingPointRelative starting point, where the point coordinates are relative coordinates
     * @param newFacing the new facing on the next square
     * @return relative coordinates on the other square
     */
    private Point wrapAroundRight(Point startingPointRelative, Direction newFacing) {
        if (startingPointRelative.x() != size - 1) {
            throw new IllegalArgumentException("Starting position is not at the right edge: " + startingPointRelative);
        }
        return switch(newFacing) {
            case LEFT  -> new Point(size - 1, size - 1 - startingPointRelative.y());
            case DOWN  -> new Point(size - 1 - startingPointRelative.y(), 0);
            case UP    -> new Point(startingPointRelative.y(), size - 1);
            case RIGHT -> new Point(0, startingPointRelative.y());
            case DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT -> throw new IllegalArgumentException("Unexpected diagonal direction: " + newFacing);
            default    -> throw new IllegalArgumentException("Unexpected facing: " + newFacing);
        };
    }
    
    /**
     * Moves a single step off this face over the bottom edge, onto another square.
     * 
     * @param startingPointRelative starting point, where the point coordinates are relative coordinates
     * @param newFacing the new facing on the next square
     * @return relative coordinates on the other square
     */
    private Point wrapAroundDown(Point startingPointRelative, Direction newFacing) {
        if (startingPointRelative.y() != size - 1) {
            throw new IllegalArgumentException("Starting position is not at the bottom edge: " + startingPointRelative);
        }
        return switch(newFacing) {
            case LEFT  -> new Point(size - 1, startingPointRelative.x());
            case DOWN  -> new Point(startingPointRelative.x(), 0);
            case UP    -> new Point(size - 1 - startingPointRelative.x(), size - 1);
            case RIGHT -> new Point(0, size - 1 - startingPointRelative.x());
            case DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT -> throw new IllegalArgumentException("Unexpected diagonal direction: " + newFacing);
            default    -> throw new IllegalArgumentException("Unexpected facing: " + newFacing);
        };
    }
    
    /**
     * Moves a single step off this face over the top edge, onto another square.
     * 
     * @param startingPointRelative starting point, where the point coordinates are relative coordinates
     * @param newFacing the new facing on the next square
     * @return relative coordinates on the other square
     */
    private Point wrapAroundUp(Point startingPointRelative, Direction newFacing) {
        if (startingPointRelative.y() != 0) {
            throw new IllegalArgumentException("Starting position is not at the top edge: " + startingPointRelative);
        }
        return switch(newFacing) {
            case LEFT  -> new Point(size - 1, size - 1 - startingPointRelative.x());
            case DOWN  -> new Point(size - 1 - startingPointRelative.x(), 0);
            case UP    -> new Point(startingPointRelative.x(), size - 1); 
            case RIGHT -> new Point(0, startingPointRelative.x());
            case DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT -> throw new IllegalArgumentException("Unexpected diagonal direction: " + newFacing);
            default    -> throw new IllegalArgumentException("Unexpected facing: " + newFacing);
        };
    }
}
