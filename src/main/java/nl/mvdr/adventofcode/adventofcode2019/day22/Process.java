package nl.mvdr.adventofcode.adventofcode2019.day22;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A complete shuffle process.
 *
 * @author Martijn van de Rijdt
 */
class Process {
    private final List<Instruction> instructions;
    
    /**
     * Parses a textual representation of a series of instructions into a shuffle process.
     * 
     * @param lines instructions from puzzle input
     * @return a complete shuffle process
     */
    static Process parse(Stream<String> lines) {
        List<Instruction> instructions = lines.filter(Predicate.not(String::isBlank))
                .map(Instruction::parse)
                .collect(Collectors.toList());
        return new Process(instructions);
    }
    
    private Process(List<Instruction> instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Performs this shuffle process on a factory-order deck.
     * 
     * @param numberOfCards number of cards in the deck
     * @return shuffled deck
     */
    List<Integer> performOnFactoryOrderDeck(int numberOfCards) {
        List<Integer> deck = IntStream.range(0, numberOfCards)
                .boxed()
                .collect(Collectors.toList());
        return perform(deck);
    }
    
    /**
     * Performs this shuffle process.
     * 
     * @param deck deck to shuffle
     * @return shuffled deck
     */
    private List<Integer> perform(List<Integer> deck) {
        List<Integer> result = deck;
        for (Instruction instruction : instructions) {
            result = instruction.perform(result);
        }
        return result;
    }
}
