package nl.mvdr.adventofcode.adventofcode2020.day16;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A train ticket.
 *
 * @author Martijn van de Rijdt
 */
record Ticket(List<Integer> fields) {

    /**
     * Parses a ticket, based on a line from the puzzle input.
     * 
     * @param line textual representation of a ticket, for example: {@code 7,3,47}
     * @return ticket
     */
    static Ticket parse(String line) {
        List<Integer> values = Stream.of(line.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        
        return new Ticket(values);
    }
}
