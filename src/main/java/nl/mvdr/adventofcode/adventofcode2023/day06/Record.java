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
        var times = parseValues("Time:", input.getFirst());
        var distances = parseValues("Distance:", input.getLast());
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
        // Note: an obvious way to optimize this for large inputs would be:
        // - binary search for an optimal button hold time
        // - binary search the first value over the record, in the values before the optimum
        // - binary search the first value no longer over the record, in the values after the optimum
        // - subtract these two values
        var result = IntStream.range(0, time)
                .map(this::computeDistance)
                .filter(d -> distance < d)
                .count();
        return Math.toIntExact(result);
    }
    
    /**
     * Computes the distance traveled if the button is held for the given amount of time.
     * 
     * @param buttonHoldTime amount of time to hold the button in milliseconds
     * @return distance traveled in millimeters
     */
    private int computeDistance(int buttonHoldTime) {
        if (buttonHoldTime < 0 || time < buttonHoldTime) {
            throw new IllegalArgumentException("Invalid button hold time: " + buttonHoldTime);
        }
        var speed = buttonHoldTime; // in millimeters per millisecond
        var travelTime = time - buttonHoldTime; // in milliseconds
        return travelTime * speed;
    }
}
