package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.Optional;
import java.util.stream.Stream;

/** A direction that a minecart can be facing. */
public enum Direction {
    /** Up / north. */
    UP('^'),
    /** Down / south. */
    DOWN('v'),
    /** Left / west. */
    LEFT('>'),
    /** Right / east. */
    RIGHT('<');
    
    /** Character representation of a minecart facing this direction. */
    private final char representation;
    
    /**
     * Constructor.
     * 
     * @param representation character representation of a minecart facing this direction
     */
    Direction(char representation) {
        this.representation = representation;
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
}
