package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * A direction that a minecart can be facing.
 *
 * @author Martijn van de Rijdt
 */
enum Direction {
    /** Up / north. */
    UP('^', true),
    /** Down / south. */
    DOWN('v', true),
    /** Left / west. */
    LEFT('>', false),
    /** Right / east. */
    RIGHT('<', false);
    
    /** Character representation of a minecart facing this direction. */
    private final char representation;
    
    /** Whether this direction is vertical. */
    private final boolean vertical;
    
    /**
     * Constructor.
     * 
     * @param representation character representation of a minecart facing this direction
     * @param vertical whether this direction is vertical
     */
    Direction(char representation, boolean vertical) {
        this.representation = representation;
        this.vertical = vertical;
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
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
