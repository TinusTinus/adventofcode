package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * A direction that a minecart can be facing.
 *
 * @author Martijn van de Rijdt
 */
enum Direction {
    /** Up / north. */
    UP('^', true, Point::aboveNeighbour),
    /** Down / south. */
    DOWN('v', true, Point::belowNeighbour),
    /** Left / west. */
    LEFT('<', false, Point::leftNeighbour),
    /** Right / east. */
    RIGHT('>', false, Point::rightNeighbour);
    
    /** Character representation of a minecart facing this direction. */
    private final char representation;
    
    /** Whether this direction is vertical. */
    private final boolean vertical;
    
    /** Function that, given a cart's location, determines the cart's next location, if it is moving in this direction. */
    private final Function<Point, Point> next;
    
    /**
     * Constructor.
     * 
     * @param representation character representation of a minecart facing this direction
     * @param vertical whether this direction is vertical
     */
    Direction(char representation, boolean vertical, Function<Point, Point> next) {
        this.representation = representation;
        this.vertical = vertical;
        this.next = next;
    }
    
    /**
     * Gives the direction represented by the given character.
     * 
     * @param representation character representation
     * @return optional direction
     */
    static Optional<Direction> of(char representation) {
        return Stream.of(Direction.values())
                .filter(direction -> direction.representation == representation)
                .findFirst();
    }
    
    /** @return the straight path matching the direction of this cart */
    TrackSection getStraightPath() {
        TrackSection result;
        if (vertical) {
            result = TrackSection.VERTICAL_STRAIGHT_PATH;
        } else {
            result = TrackSection.HORIZONTAL_STRAIGHT_PATH;
        }
        return result;
    }
    
    
    /**
     * Given a cart's location, determines the cart's next location, if it is moving in this direction.
     * 
     * @param location current location
     * @return next location
     */
    Point nextLocation(Point location) {
        return next.apply(location);
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
