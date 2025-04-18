package nl.mvdr.adventofcode.adventofcode2023.day04;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiSet.Entry;

/**
 * A scratchcard.
 *
 * @param id this card's unique identifier
 * @param winningNumbers the numbers on the left, that is, the winning numbers
 * @param ourNumbers the numbers on the right
 * @author Martijn van de Rijdt
 */
record Scratchcard(int id, Set<Integer> winningNumbers, List<Integer> ourNumbers) {
    
    private static final String CARD_PREFIX = "Card";

    /**
     * Parses the textual representation of a scratchcard.
     *  
     * @param text textual representation, for example: "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1"
     * @return scratchcard
     */
    static Scratchcard parse(String text) {
        var parts = text.split(": ");
        if (parts.length != 2 || !parts[0].startsWith(CARD_PREFIX)) {
            throw new IllegalArgumentException("Unable to parse scratchcard: " + text);
        }
        var id = Integer.parseInt(parts[0].substring(CARD_PREFIX.length()).trim());
        return parse(id, parts[1]);
    }

    /**
     * Parses the textual representation of scratchcard numbers.
     * 
     * @param id the scratchcard id
     * @param text textual representation of all numbers on a card, for example: " 1 21 53 59 44 | 69 82 63 72 16 21 14  1"
     * @return scratchcard
     */
    private static Scratchcard parse(int id, String text) {
        var parts = text.split(" \\| ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse numbers: " + text);
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
     * Finds the card with the given id.
     * 
     * @param scratchcards collection of scratch cards; may contain multiple copies of the same card
     * @param cardId card id
     * @return one of the copies of the card with the given id
     */
    static Scratchcard findCard(MultiSet<Scratchcard> scratchcards, int cardId) {
        return scratchcards.entrySet()
                .stream()
                .map(Entry::getElement)
                .filter(card -> card.id() == cardId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Card " + cardId + " not found"));
    }

    /**
     * @return the count of our numbers which also occur as a winning number
     */
    int countWins() {
        var result = ourNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
        return Math.toIntExact(result);
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
            // Note that computing powers of 2 can be implemented efficiently by bitshifting: pow(2, n)  =  1 << n
            result = 1 << (wins - 1);
        }
        return result;
    }
}
