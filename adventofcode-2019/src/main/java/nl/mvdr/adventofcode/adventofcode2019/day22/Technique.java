package nl.mvdr.adventofcode.adventofcode2019.day22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/**
 * A shuffling technique.
 *
 * @author Martijn van de Rijdt
 */
enum Technique implements PreviousIndexFunction {
    /** Deal into a new stack. */
    DEAL_INTO_NEW_STACK("deal into new stack",
            (deck, _) -> {
                List<Integer> result = new ArrayList<>(deck);
                Collections.reverse(result);
                return result;
            },
            (newIndex, deckSize, _) -> deckSize - newIndex - 1),
    
    /** Cut the cards. */
    CUT("cut ",
            (deck, n) -> {
                int numberOfCards = n.orElseThrow();
                if (numberOfCards < 0) {
                    numberOfCards = deck.size() + numberOfCards;
                }
                List<Integer> result = new ArrayList<>(deck.size());
                deck.subList(numberOfCards, deck.size()).forEach(result::add);
                deck.subList(0, numberOfCards).forEach(result::add);
                return result;
            },
            (newIndex, deckSize, n) -> {
                long numberOfCards = n.orElseThrow();
                if (numberOfCards < 0L) {
                    numberOfCards = deckSize + numberOfCards;
                }
                
                return (newIndex + numberOfCards) % deckSize;
            }),
    
    /** Deal with increment. */
    DEAL_WITH_INCREMENT("deal with increment ",
            (deck, n) -> {
                List<Integer> result = new ArrayList<>(Collections.nCopies(deck.size(), null));
                IntStream.range(0, deck.size())
                        .forEach(i -> result.set((i * n.orElseThrow()) % deck.size(), deck.get(i)));
                return result;
            },
            (newIndex, deckSize, n) -> {
                // Note: optimization may be possible; can we do this without a linear search?
                OptionalLong result = OptionalLong.empty();
                long j = 0L;
                while (result.isEmpty()) {
                    if ((j * n.orElseThrow()) % deckSize == newIndex) {
                        result = OptionalLong.of(j);
                    } else {
                        j++;
                    }
                }
                return result.orElseThrow();
            });
    
    private final String textualRepresentation;
    private final BiFunction<List<Integer>, OptionalInt, List<Integer>> implementation;
    private final PreviousIndexFunction previousIndexFunction;
    
    Technique(String textualRepresentation, BiFunction<List<Integer>, OptionalInt, List<Integer>> implementation, PreviousIndexFunction previousIndexFunction) {
        this.textualRepresentation = textualRepresentation;
        this.implementation = implementation;
        this.previousIndexFunction = previousIndexFunction;
    }
    
    /**
     * Performs this technique.
     * 
     * @param deck starting state of the deck
     * @param n parameter; required for {@link #CUT} and {@link #DEAL_WITH_INCREMENT}
     * @return deck after applying this technique
     */
    List<Integer> perform(List<Integer> deck, OptionalInt n) {
        return implementation.apply(deck, n);
    }
    
    @Override
    public long computePreviousIndex(long newIndex, long deckSize, OptionalInt n) {
        return previousIndexFunction.computePreviousIndex(newIndex, deckSize, n);
    }
    
    String getTextualRepresentation() {
        return textualRepresentation;
    }
}
