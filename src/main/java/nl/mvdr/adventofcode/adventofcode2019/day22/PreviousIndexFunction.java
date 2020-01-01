package nl.mvdr.adventofcode.adventofcode2019.day22;

import java.util.OptionalInt;

/**
 * Function which computes the previous index of a card, before a {@link Technique} was executed.
 *
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
public interface PreviousIndexFunction {
    /**
     * Computes the previous index of a card, before a technique was executed.
     * 
     * @param newIndex index of the card after performing the technique
     * @param deckSize size of the deck (total number of cards)
     * @param n parameter; required for {@link Technique#CUT} and {@link Technique#DEAL_WITH_INCREMENT}
     * @return index of the card in the deck before performing the technique
     */
    long computePreviousIndex(long newIndex, long deckSize, OptionalInt n);
}
