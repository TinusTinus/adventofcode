package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MultiSet;
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
     * @param cardsList cards
     * @return type
     */
    @SuppressWarnings("unchecked") // If the collection contains a joker, it must be a collection of Part2Cards
    static Type of(List<? extends Card> cardsList) {
        if (cardsList.size() != 5) {
            throw new IllegalArgumentException("Invalid hand");
        }
        
        MultiSet<? extends Card> cards = cardsList.stream()
                .collect(Collectors.toCollection(HashMultiSet::new));
        
        if (cards.contains(Part2Card.JOKER)) {
            cards = replaceJokers((MultiSet<Part2Card>)cards);
        }
        
        return of(cards);
    }

    /**
     * Returns a copy of the given multiset, where any Jokers have been replaced by the card as which they should be counted.
     * 
     * @param cards cards
     * @param cards with Jokers replaced
     */
    private static MultiSet<Part2Card> replaceJokers(MultiSet<Part2Card> cards) {
        var jokerCount = cards.getCount(Part2Card.JOKER);
        
        MultiSet<Part2Card> result = new HashMultiSet<>(cards);
        
        // Remove the Jokers.
        result.remove(Part2Card.JOKER, jokerCount);
        
        // Determine whichever other card occurs most frequently.
        // We should count the Jokers as additional occurrences of this card, to get the strongest type.
        var mostFrequentCard = result.entrySet()
                .stream()
                .max(Comparator.comparing(Entry::getCount))
                .map(Entry::getElement)
                .map(card -> card)
                // if the hand contained five jokers, just count them as any different card, so we get a five of a kind
                .orElse(Part2Card.TWO);
        result.add(mostFrequentCard, jokerCount);
        
        return result;
    }
    
    /**
     * Determines the type of a hand containing the given cards.
     * 
     * Note that this method expects the given multiset not to contain any Jokers.
     * Instead, any Jokers should be replaced by the cards as which they should be counted.
     *  
     * @param cards cards, where any Jokers have already been replaced
     * @return type
     */
    private static Type of(MultiSet<? extends Card> cards) {
        var counts = cards.entrySet()
                .stream()
                .mapToInt(Entry::getCount)
                .boxed()
                .toList();
        
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
            throw new IllegalStateException("Unexpected counts: " + counts);
        }
        return result;
    }
}
