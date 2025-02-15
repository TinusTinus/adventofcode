package nl.mvdr.adventofcode.point;

/**
 * Functional interface defining how to map a {code char} value to an object.
 *
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
public interface CharacterMapper<T> {
    /**
     * Converts a character to an object.
     * 
     * @param character character value
     * @return object
     */
    T map(char character);
}
