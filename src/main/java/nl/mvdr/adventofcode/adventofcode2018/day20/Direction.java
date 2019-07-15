package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.function.Function;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Direction.
 *
 * @author Martijn van de Rijdt
 */
public enum Direction implements RoomMapExpression {
    
    /** North. */
    NORTH(Point::northNeighbour),
    
    /** East. */
    EAST(Point::eastNeighbour),
    
    /** South. */
    SOUTH(Point::southNeighbour),
    
    /** West. */
    WEST(Point::westNeighbour);
    
    private final Function<Point, Point> neighbourGetter;
    
    Direction(Function<Point, Point> neighbourGetter) {
        this.neighbourGetter = neighbourGetter;
    }
    
    /** @return single-character representation of this direction */
    char getCharacterRepresentation() {
        return name().charAt(0);
    }
    
    /**
     * Returns the neighbouring point in the given direction.
     * 
     * @param point base point
     * @return the point one off in the given direction
     */
    Point getNeighbour(Point point) {
        return neighbourGetter.apply(point);
    }
    
    /**
     * Parses the given character into a direction.
     * 
     * @param c character representation of a direction: N, E, S or W
     * @return direction
     */
    static Direction parse(char c) {
        return Stream.of(Direction.values())
                .filter(value -> value.getCharacterRepresentation() == c)
                .findFirst()
                .get();
    }
    
    @Override
    public String toString() {
        return "" + getCharacterRepresentation();
    }
}
