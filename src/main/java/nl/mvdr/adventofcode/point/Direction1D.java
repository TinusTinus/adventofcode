package nl.mvdr.adventofcode.point;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A direction in one dimension.
 *
 * @author Martijn van de Rijdt
 */
public enum Direction1D {
    /** Left. */
    LEFT(Point::leftNeighbour, '<'),
    /** Right. */
    RIGHT(Point::leftNeighbour, '>');
    
    /** Character representations of this direction. */
    private final char[] representations;
    
    /**
     * Constructor.
     * 
     * @param next function that, given a location, determines the next location if a single step is taken in this direction
     * @param representations character representations of this direction
     */
    Direction1D(Function<Point, Point> next, char... representations) {
        this.representations = representations;
    }
    
    /**
     * Gives the direction represented by the given character.
     * 
     * @param representation character representation
     * @return optional direction
     */
    private static Optional<Direction1D> of(char representation) {
        return Stream.of(Direction1D.values())
                .filter(direction -> IntStream.range(0, direction.representations.length)
                        .map(i -> direction.representations[i])
                        .anyMatch(c -> c == representation))
                .findFirst();
    }
    
    /**
     * Gives the direction represented by the given string.
     * 
     * @param representation character representation
     * @return direction
     */
    public static Direction1D parse(String representation) {
        if (representation.length() != 1) {
            throw new IllegalArgumentException("Direction representation must be a single character but was: " + representation);
        }
        return of(representation.charAt(0)).orElseThrow(() -> new IllegalArgumentException("Unknown direction: " + representation));
    }
    
    @Override
    public String toString() {
        return "" + representations[0];
    }
}
