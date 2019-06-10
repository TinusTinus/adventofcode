package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A single square section of a mine cart track.
 *
 * @author Martijn van de Rijdt
 */
enum TrackSection {
    /** Empty square. */
    EMPTY(' ', Map.of()),
    /** A vertical straight section, allowing up-down and down-up movement. */
    VERTICAL_STRAIGHT_PATH('|', Map.of(Direction.UP, Direction.UP, Direction.DOWN, Direction.DOWN)),
    /** A horizontal straight section, allowing left-right and right-left movement. */
    HORIZONTAL_STRAIGHT_PATH('-', Map.of(Direction.LEFT, Direction.LEFT, Direction.RIGHT, Direction.RIGHT)),
    /** A curve, allowing down-left, up-right, left-up and right-down movement. */
    CURVE_DOWN_LEFT('/', Map.of(Direction.DOWN, Direction.LEFT, Direction.UP, Direction.RIGHT, Direction.LEFT, Direction.UP, Direction.RIGHT, Direction.DOWN)),
    /** A curve, allowing down-right, up-left, left-down and right-up movement. */
    CURVE_DOWN_RIGHT('\\', Map.of(Direction.DOWN, Direction.RIGHT, Direction.UP, Direction.LEFT, Direction.LEFT, Direction.UP, Direction.RIGHT, Direction.UP)),
    /** An intersecion, which allows a cart to turn. */
    // Empty directions: the next direction depends on the cart's own state.
    INTERSECTION('+', Map.of());
    
    /** Character representation of this section of the tracks. */
    private final char representation;
    
    /** Map indicating, given a cart that enters this section facing a direction, what its next direction should be. */
    private final Map<Direction, Direction> nextDirections;
    
    TrackSection(char representation, Map<Direction, Direction> nextDirections) {
        this.representation = representation;
        this.nextDirections = nextDirections;
    }
    
    /**
     * Next direction for a cart that has just entered this section.
     * 
     * Note: do not call this method on {@link #INTERSECTION}!
     * A cart's next direction on an intersection depends on its own state, beyond its direction.
     * 
     * @param currentDirection cart's current direction
     * @return cart's next direction
     */
    Direction getNextDirection(Direction currentDirection) {
        Direction result = nextDirections.get(currentDirection);
        return Objects.requireNonNull(result, "Invalid direction " + currentDirection + " on section " + this);
    }
    
    /**
     * Gives the track section represented by the given character.
     * 
     * @param representation character representation; should be a valid value
     * @return track section
     */
    static TrackSection of(char representation) {
        return Stream.of(TrackSection.values())
                .filter(section -> section.representation == representation)
                .findFirst()
                .get();
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
