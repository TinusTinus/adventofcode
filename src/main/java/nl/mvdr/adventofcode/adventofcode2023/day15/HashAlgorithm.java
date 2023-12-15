package nl.mvdr.adventofcode.adventofcode2023.day15;

/**
 * Implementation of the Holiday ASCII String Helper (HASH) algorithm.
 *
 * @author Martijn van de Rijdt
 */
class HashAlgorithm {
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
                .reduce(0, (currentValue, currentCharacter) -> ((currentValue + currentCharacter) * 17) % 256);
    }
}
