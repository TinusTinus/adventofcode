package nl.mvdr.adventofcode.adventofcode2017.knothash;

/**
 * Object which is capable of computing a <a href="https://adventofcode.com/2017/day/10">Knot Hash</a>.
 *
 * @author Martijn van de Rijdt
 */
public interface KnotHasher {
    /**
     * Computes the knot hash of the given string.
     * 
     * @param input input string
     * @return string representation of the string's knot hash: consists of 32 hexadecimal numbers
     */
    String knotHash(String input);
}
