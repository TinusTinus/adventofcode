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
record Number(int value, int orderNumber) {
    
    /**
     * Parses the puzzle input.
     * 
     * @param lines puzzle input
     * @return list of numbers
     */
    static List<Number> parse(List<String> lines) {
        return IntStream.range(0, lines.size())
                .mapToObj(i -> new Number(getValue(lines.get(i)), i))
                .toList();
    }

    /**
     * Parses the given line into a value.
     * 
     * @param line line from puzzle input
     * @return value corresponding to the given line
     */
    private static int getValue(String line) {
        return Integer.parseInt(line);
    }
    
    @Override
    public String toString() {
        return "" + value;
    }
}
