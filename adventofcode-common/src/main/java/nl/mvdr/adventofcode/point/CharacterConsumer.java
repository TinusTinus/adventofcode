package nl.mvdr.adventofcode.point;

/**
 * Consumer for use when parsing a string representation of a two-dimensional map.
 *
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
public interface CharacterConsumer {
    /**
     * Handles a single character from the given map.
     * 
     * @param point coordinates of the character in the given input
     * @param character the character from the input
     */
    void accept(Point point, char character);
}
