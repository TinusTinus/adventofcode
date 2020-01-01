package nl.mvdr.adventofcode.adventofcode2019.day22;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Stream;

/**
 * An instruction to perform a single technique.
 *
 * @author Martijn van de Rijdt
 */
class Instruction {
    /** Which technique to perform. */
    private final Technique technique;
    /** Optional parameter for performing the technique. */
    private final OptionalInt n;
    
    /**
     * Parses a textual representation of an instruction.
     * 
     * @param text textual representation
     * @return instruction
     */
    static Instruction parse(String text) {
        Technique technique = Stream.of(Technique.values())
                .filter(value -> text.startsWith(value.getTextualRepresentation()))
                .findFirst()
                .orElseThrow();
        
        String parameter = text.substring(technique.getTextualRepresentation().length(), text.length());
        OptionalInt n;
        if ("".equals(parameter)) {
            n = OptionalInt.empty();
        } else {
            n = OptionalInt.of(Integer.parseInt(parameter));
        }
        
        return new Instruction(technique, n);
    }
    
    /**
     * Constructor.
     * 
     * @param technique which technique to perform
     * @param n optional parameter for performing the technique
     */
    private Instruction(Technique technique, OptionalInt n) {
        super();
        this.technique = technique;
        this.n = n;
    }
    
    /**
     * Performs this instruction.
     * 
     * @param deck starting state of the deck
     * @return deck after performing this instruction
     */
    List<Integer> perform(List<Integer> deck) {
        return technique.perform(deck, n);
    }
    
    /**
     * Computes the previous index of a card, before this instruction was executed.
     * 
     * @param newIndex index of the card after performing this instruction
     * @param deckSize size of the deck
     * @return index of the card in the deck before performing this instruction
     */
    long computePreviousIndex(long newIndex, long deckSize) {
        return technique.computePreviousIndex(newIndex, deckSize, n);
    }
}
