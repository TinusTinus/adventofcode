package nl.mvdr.adventofcode.adventofcode2023.day04;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A scratchcard.
 *
 * @param winningNumbers the numbers on the left, that is, the winning numbers
 * @param ourNumbers the numbers on the right
 * @author Martijn van de Rijdt
 */
record Scratchcard(Set<Integer> winningNumbers, List<Integer> ourNumbers) {
    /**
     * Parses the textual representation of a scratchcard.
     *  
     * @param text textual representation, for example: "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"
     * @return scratchcard
     */
    static Scratchcard parse(String text) {
        var parts = text.split(": ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse scratchcard: " + text);
        }
        // Discard the first part (we do not need the card id)
        parts = parts[1].split(" \\| ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse scratchcard: " + text);
        }
        var winningNumbers = parseNumbers(parts[0])
                .collect(Collectors.toSet());
        var ourNumbers = parseNumbers(parts[1])
                .toList();
        return new Scratchcard(winningNumbers, ourNumbers);
    }
    
    /**
     * Parses the given text into a stream of numbers.
     * 
     * @param text textual representation of a bunch of numbers, for example: "69 82 63 72 16 21 14  1"
     * @return the numbers represented by the given text
     */
    private static Stream<Integer> parseNumbers(String text) {
        var parts = text.trim().split(" +");
        return Stream.of(parts)
                .map(Integer::valueOf);
    }

    /**
     * @return the count of our numbers which also occur as a winning number
     */
    private long countWins() {
        return ourNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }
    
    /**
     * @return score of this scratchcard, in points
     */
    long score() {
        var wins = countWins();
        long result;
        if (wins == 0L) {
            result = 0L;
        } else {
            // score = pow(2, wins - 1)
            // Note that powers of 2 can be implemented efficiently by bitshifting: pow(2, n)  =  1 << n
            result = 1L << (wins - 1);
        }
        return result;
    }
}
