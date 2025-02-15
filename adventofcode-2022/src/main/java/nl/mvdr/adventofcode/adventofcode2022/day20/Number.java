package nl.mvdr.adventofcode.adventofcode2022.day20;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Representation of a number.
 * 
 * @param value the number value
 * @param orderNumber the index of this number in the order it originally appeared in the encrypted file 
 *
 * @author Martijn van de Rijdt
 */
record Number(long value, int orderNumber) {
    
    private static final long DECRYPTION_KEY = 811589153L;
    
    /**
     * Parses the puzzle input.
     * 
     * @param lines puzzle input
     * @param applyDecryptionKey whether the decryption key should be applied to each value
     * @return list of numbers
     */
    static List<Number> parse(List<String> lines, boolean applyDecryptionKey) {
        return IntStream.range(0, lines.size())
                .mapToObj(i -> new Number(getValue(lines.get(i), applyDecryptionKey), i))
                .toList();
    }

    /**
     * Parses the given line into a value.
     * 
     * @param line line from puzzle input
     * @param applyDecryptionKey whether the decryption key should be applied to the value
     * @return value corresponding to the given line
     */
    private static long getValue(String line, boolean applyDecryptionKey) {
        long result = Long.parseLong(line);
        if (applyDecryptionKey) {
            result = result * DECRYPTION_KEY;
        }
        return result;
    }
    
    @Override
    public String toString() {
        return "" + value;
    }
}
