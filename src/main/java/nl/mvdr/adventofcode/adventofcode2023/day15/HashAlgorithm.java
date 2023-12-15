package nl.mvdr.adventofcode.adventofcode2023.day15;

/**
 * Implementation of the Holiday ASCII String Helper (HASH) algorithm.
 *
 * @author Martijn van de Rijdt
 */
class HashAlgorithm {
    private static final int FACTOR = 17;
    /**
     * Divisor used when computing a hash.
     * 
     * This number is also the number of possible hash values.
     * Foe all hash values: 0 &leq; hash value &lt; DIVISOR.
     */
    static final int DIVISOR = 256;

    /** Private constructor to prevent utility class instantiation. */
    private HashAlgorithm() {
        super();
    }
    
    /**
     * Computes the HASH of a string.
     * 
     * @param string string to hash
     * @return hash
     */
    static int hash(String string) {
        return string.chars()
                .reduce(0, (currentValue, currentCharacter) -> ((currentValue + currentCharacter) * FACTOR) % DIVISOR);
    }
}
