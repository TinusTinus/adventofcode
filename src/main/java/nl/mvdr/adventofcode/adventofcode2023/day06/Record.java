package nl.mvdr.adventofcode.adventofcode2023.day06;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A boat race record.
 *
 * @param time the time in milliseconds
 * @param distance the distance in millimeters
 * @author Martijn van de Rijdt
 */
record Record(int time, int distance) {
    
    /**
     * Parses the puzzle input.
     * 
     * @param input puzzle input, which must consist of a line of times followed by a line of distances
     * @return list of records
     */
    static List<Record> parse(List<String> input) {
        if (input.size() != 2) {
            throw new IllegalArgumentException("Invalid input: " + input);
        }
        var times = parseValues("Time: ", input.getFirst());
        var distances = parseValues("Distance: ", input.getLast());
        if (times.size() != distances.size()) {
            throw new IllegalArgumentException("Numbers of times (" + times.size() + ") and distances ("
                    + distances.size() + ") do not match in input: " + input);
        }
        
        return IntStream.range(0, times.size())
                .mapToObj(i -> new Record(times.get(i).intValue(), distances.get(i).intValue()))
                .toList();
    }
    
    /**
     * Parses a line from the puzzle input.
     * 
     * @param prefix expected prefix, for example: "Time:"
     * @param text text which should start with the given prefix, followed by a list of numbers, for example: "Time:      7  15   30"
     * @return the numbers
     */
    private static List<Integer> parseValues(String prefix, String text) {
        if (!text.startsWith(prefix)) {
            throw new IllegalArgumentException("Missing prefix '" + prefix + "': " + text);
        }
        var parts = text.substring(prefix.length())
                .trim()
                .split(" +");
        return Stream.of(parts)
                .map(Integer::valueOf)
                .toList();
    }
    
    /**
     * @return the number of possible ways to beat this record
     */
    int countWaysToBeat() {
        return 0; // TODO implement
    }
}
