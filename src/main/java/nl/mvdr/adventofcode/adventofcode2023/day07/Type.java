package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MultiSet.Entry;
import org.apache.commons.collections4.multiset.HashMultiSet;

/**
 * The type of a hand.
 * 
 * The values of this enum are ordered weak-to-strong.
 * (Note: this is as opposed to their order in the puzzle description.)
 *
 * @author Martijn van de Rijdt
 */
enum Type {
    /**
     * High card, where all cards' labels are distinct: 23456.
     */
    HIGH_CARD,
    /**
     * One pair, where two cards share one label, and the other three cards
     * have a different label from the pair and each other: A23A4.
     */
    ONE_PAIR,
    /**
     * Two pair, where two cards share one label, two other cards share a
     * second label, and the remaining card has a third label: 23432.
     */
    TWO_PAIR,
    /**
     * Three of a kind, where three cards have the same label, and the
     * remaining two cards are each different from any other card in the
     * hand: TTT98.
     */
    THREE_OF_A_KIND,
    /**
     * Full house, where three cards have the same label, and the remaining
     * two cards share a different label: 23332.
     */
    FULL_HOUSE,
    /**
     * Four of a kind, where four cards have the same label and one card has
     * a different label: AA8AA.
     */
    FOUR_OF_A_KIND,
    /**
     * Five of a kind, where all five cards have the same label: AAAAA.
     */
    FIVE_OF_A_KIND;
    
    /**
     * Determines the type of a hand containing the given cards.
     *  
     * @param cards cards
     * @return
     */
    static Type of(List<? extends Card> cards) {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("Invalid hand");
        }
        
        var counts = cards.stream()
                .collect(Collectors.toCollection(HashMultiSet::new))
                .entrySet()
                .stream()
                .mapToInt(Entry::getCount)
                .boxed()
                .toList();
        
        // TODO take Jokers into account here
        
        Type result;
        if (counts.equals(List.of(Integer.valueOf(5)))) {
            result = FIVE_OF_A_KIND;
        } else if (counts.contains(Integer.valueOf(4))) {
            result = FOUR_OF_A_KIND;
        } else if (counts.contains(Integer.valueOf(3)) && counts.contains(Integer.valueOf(2))) {
            result = FULL_HOUSE;
        } else if (counts.contains(Integer.valueOf(3))) {
            result = THREE_OF_A_KIND;
        } else if (counts.stream().filter(i -> i.intValue() == 2).count() == 2L) {
            result = TWO_PAIR;
        } else if (counts.contains(Integer.valueOf(2))) {
            result = ONE_PAIR;
        } else if (counts.stream().filter(i -> i.intValue() == 1).count() == 5L) {
            result = HIGH_CARD;
        } else {
            throw new IllegalStateException("Unexpected counts for hand " + cards + ": " + counts);
        }
        return result;
    }
}
