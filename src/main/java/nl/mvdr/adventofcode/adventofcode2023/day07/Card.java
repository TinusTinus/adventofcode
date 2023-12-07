package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.stream.Stream;

/**
 * A card.
 * 
 * Cards are ordered in <em>increasing</em> value.
 * (Note: this is as opposed to their order in the puzzle description.)
 *
 * @author Martijn van de Rijdt
 */
enum Card {
    
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');
    
    private final char representation;
    
    /**
     * Parses a single-character representation of a card.
     * 
     * @param representation card representation, such as '5' or 'Q'
     * @return the corresponding card
     */
    static Card parse(char representation) {
        return Stream.of(values())
                .filter(card -> card.representation == representation)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to parse card: " + representation));
    }
    
    /**
     * Constructor.
     * 
     * @param representation single-character representation of the card
     */
    Card(char representation) {
        this.representation = representation;
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
