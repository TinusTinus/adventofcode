package nl.mvdr.adventofcode.adventofcode2022.day11;

import java.util.List;
import java.util.stream.Stream;

/**
 * Current monkey situation.
 *
 * @author Martijn van de Rijdt
 */
record State(List<Monkey> monkeys) {
    
    /**
     * Parses puzzle input.
     * 
     * @param lines puzzle input
     * @return initial state
     */
    static State parse(Stream<String> lines) {
        var monkeys = Monkey.parse(lines.toList());
        return new State(monkeys);
    }
}
