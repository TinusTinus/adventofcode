package nl.mvdr.adventofcode.adventofcode2023.day15;

/**
 * Implementation of the Holiday ASCII String Helper (HASH) algorithm.
 *
 * @author Martijn van de Rijdt
 */
class HashAlgorithm {
    
    /**
     * The number of possible hash values.
     * For all hash values: 0 &leq; hash value &lt; DIVISOR.
     * 
     * Used as the divisor when computing a hash.
     */
    static final int NUMBER_OF_VALUES = 256;
    /**
     * Multiplication factor used when computing a hash.
     */
    private static final int FACTOR = 17;
    
    /** Private constructor to prevent utility class instantiation. */
    private HashAlgorithm() {
        super();
    }
    
    /**
     * Computes the HASH value of a string.
     * 
     * @param string string to hash
     * @return hash
     */
    static int hash(String string) {
        return string.chars()
                .reduce(0, (currentValue, currentCharacter) -> ((currentValue + currentCharacter) * FACTOR) % NUMBER_OF_VALUES);
    }
}
