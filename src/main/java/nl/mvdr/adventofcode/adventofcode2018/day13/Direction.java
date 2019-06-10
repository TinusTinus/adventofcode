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
    UP('^', TrackSection.VERTICAL_STRAIGHT_PATH),
    /** Down / south. */
    DOWN('v', TrackSection.VERTICAL_STRAIGHT_PATH),
    /** Left / west. */
    LEFT('>', TrackSection.HORIZONTAL_STRAIGHT_PATH),
    /** Right / east. */
    RIGHT('<', TrackSection.HORIZONTAL_STRAIGHT_PATH);
    
    /** Character representation of a minecart facing this direction. */
    private final char representation;
    
    /**
     * The straight path matching the direction of this cart.
     * 
     * On an initial map, the track under each cart is a straight path matching the direction the cart is facing.
     */
    private final TrackSection straightPath;
    
    /**
     * Constructor.
     * 
     * @param representation character representation of a minecart facing this direction
     * @param straightPath the straight path corresponding to this direction
     */
    Direction(char representation, TrackSection straightPath) {
        this.representation = representation;
        this.straightPath = straightPath;
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
    
    TrackSection getStraightPath() {
        return straightPath;
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
