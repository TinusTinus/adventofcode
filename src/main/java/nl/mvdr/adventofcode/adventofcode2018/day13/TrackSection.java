package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.stream.Stream;

/**
 * A single square section of a mine cart track.
 *
 * @author Martijn van de Rijdt
 */
enum TrackSection {
    /** Empty square. */
    EMPTY(' '),
    /** A vertical straight section, allowing up-down and down-up movement. */
    VERTICAL_STRAIGHT_PATH('|'),
    /** A horizontal straight section, allowing left-right and right-left movement. */
    HORIZONTAL_STRAIGHT_PATH('-'),
    /** A curve, allowing down-left, up-right, left-up and right-down movement. */
    CURVE_DOWN_LEFT('/'),
    /** A curve, allowing down-right, up-left, left-down and right-up movement. */
    CURVE_DOWN_RIGHT('\\'),
    /** An intersecion, which allows a cart to turn. */
    INTERSECTION('+');
    
    private final char representation;
    
    TrackSection(char representation) {
        this.representation = representation;
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
