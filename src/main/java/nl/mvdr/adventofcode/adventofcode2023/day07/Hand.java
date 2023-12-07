package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A hand of cards.
 * 
 * Hands are comparable, where a stronger hand is greater than a weaker hand.
 *
 * @param cards the cards; should contain exactly five cards
 * @param type the type of this hand
 * @author Martijn van de Rijdt
 */
record Hand(List<Card> cards, Type type) implements Comparable<Hand> {
    /**
     * Constructor.
     * 
     * @param cards cards in the hand
     */
    Hand(List<Card> cards) {
        this(cards, Type.of(cards));
    }
    
    /**
     * Parses a textual representation of a hand of cards.
     * 
     * @param text textual representation, for example: "32T3K"
     * @return the hand represented by the given text
     */
    static Hand parse(String text) {
        var cards = text.chars()
                .mapToObj(c -> Card.parse((char)c))
                .toList();
        return new Hand(cards);
    }
    
    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining());
    }

    @Override
    public int compareTo(Hand other) {
        var result = this.type().compareTo(other.type());
        var cardIndex = 0;
        while (result == 0 && cardIndex < 5) {
            result = this.cards().get(cardIndex).compareTo(other.cards().get(cardIndex));
            cardIndex++;
        }
        return result;
    }
}
