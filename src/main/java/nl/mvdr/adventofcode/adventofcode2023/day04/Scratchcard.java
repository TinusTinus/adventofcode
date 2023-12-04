package nl.mvdr.adventofcode.adventofcode2023.day04;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A scratchcard.
 *
 * @param id this card's unique identifier
 * @param winningNumbers the numbers on the left, that is, the winning numbers
 * @param ourNumbers the numbers on the right
 * @author Martijn van de Rijdt
 */
record Scratchcard(int id, Set<Integer> winningNumbers, List<Integer> ourNumbers) {
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
        var id = Integer.parseInt(parts[0].substring("Card".length()).trim());
        parts = parts[1].split(" \\| ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse scratchcard: " + text);
        }
        var winningNumbers = parseNumbers(parts[0])
                .collect(Collectors.toSet());
        var ourNumbers = parseNumbers(parts[1])
                .toList();
        return new Scratchcard(id, winningNumbers, ourNumbers);
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
    private int countWins() {
        return Math.toIntExact(ourNumbers.stream()
                .filter(winningNumbers::contains)
                .count());
    }
    
    /**
     * @return score of this scratchcard, in points
     */
    int score() {
        var wins = countWins();
        int result;
        if (wins == 0) {
            result = 0;
        } else {
            // score = pow(2, wins - 1)
            // Note that powers of 2 can be implemented efficiently by bitshifting: pow(2, n)  =  1 << n
            result = 1 << (wins - 1);
        }
        return result;
    }
}
