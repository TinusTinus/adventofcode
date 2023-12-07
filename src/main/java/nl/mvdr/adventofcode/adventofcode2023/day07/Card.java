package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.stream.Stream;

/**
 * A card.
 * 
 * Cards are ordered in <em>increasing</em> value.
 * (Note: this is as opposed to their order in the puzzle description.)
 *
 * @param <C> concrete card type
 * @author Martijn van de Rijdt
 */
interface Card<C> extends Comparable<C> {
    /**
     * Parses a single-character representation of a card.
     * 
     * @param C the concrete card class
     * @param representation card representation, such as '5' or 'Q'
     * @param cardClass the concrete card class
     * @return the corresponding card
     */
    static <C extends Card<C>> C parse(char representation, Class<C> cardClass) {
        if (!cardClass.isEnum()) {
            throw new IllegalArgumentException("Must be an enum: " + cardClass);
        }
        return Stream.of(cardClass.getEnumConstants())
                .filter(card -> card.getRepresentation() == representation)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to parse card: " + representation));
    }
    
    /**
     * @return single-character representation of the card
     */
    char getRepresentation();
}
