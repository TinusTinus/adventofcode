package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A hand of cards.
 * 
 * Hands are comparable, where a stronger hand is greater than a weaker hand.
 *
 * @param <C> the concrete card class
 * @param cards the cards; must contain exactly five cards
 * @param type the type of this hand
 * @author Martijn van de Rijdt
 */
record Hand<C extends Card<C>>(List<C> cards, Type type) implements Comparable<Hand<C>> {
    /**
     * Constructor.
     * 
     * @param cards cards in the hand
     */
    Hand(List<C> cards) {
        this(cards, Type.of(cards));
    }
    
    /**
     * Parses a textual representation of a hand of cards.
     * 
     * @param <C> the concrete card class
     * @param text textual representation, for example: "32T3K"
     * @param cardClass the concrete card class
     * @return the hand represented by the given text
     */
    static <C extends Enum<C> & Card<C>> Hand<C> parse(String text, Class<C> cardClass) {
        var cards = text.chars()
                .mapToObj(c -> Card.parse((char)c, cardClass))
                .toList();
        return new Hand<>(cards);
    }
    
    @Override
    public String toString() {
        return cards.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    @Override
    public int compareTo(Hand<C> other) {
        var result = this.type().compareTo(other.type());
        var cardIndex = 0;
        while (result == 0 && cardIndex < 5) {
            result = this.cards().get(cardIndex).compareTo(other.cards().get(cardIndex));
            cardIndex++;
        }
        return result;
    }
}
