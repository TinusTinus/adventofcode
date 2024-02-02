package nl.mvdr.adventofcode.adventofcode2016.day02;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 2 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/2">Bathroom Security</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class BathroomSecurity implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BathroomSecurity.class);
    
    private final Map<Point, String> keypad;
    
    /**
     * Constructor.
     * 
     * @param keypad keypad, represented as a map of the location of each button to the value on each button;
     *      should contain a "5"
     */
    BathroomSecurity(Map<Point, String> keypad) {
        super();
        this.keypad = keypad;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return keypad's security code
     */
    @Override
    public String solve(Stream<String> lines) {
        List<List<Direction>> instructions = lines.filter(Predicate.not(String::isEmpty))
                .map(this::parse)
                .toList();
        
        // Start at "5"
        Point currentKey = keypad.entrySet().stream()
                .filter(entry -> "5".equals(entry.getValue()))
                .map(Entry::getKey)
                .findFirst()
                .orElseThrow();
        
        StringBuilder result = new StringBuilder();
        for (List<Direction> instruction : instructions) {
            for (Direction direction : instruction) {
                Point next = direction.move(currentKey);
                if (keypad.containsKey(next)) {
                    currentKey = next;
                    LOGGER.debug("Moved in {} direction to key {}", direction, keypad.get(currentKey));
                } else {
                    LOGGER.debug("Ignored {} direction on key {}", direction, keypad.get(currentKey));
                }
            }
            String value = keypad.get(currentKey);
            LOGGER.debug("Pressed key {}", value);
            result.append(value);
        }
        
        return result.toString();
    }
    
    private List<Direction> parse(String line) {
        return line.chars()
                .mapToObj(c -> Direction.of((char)c))
                .map(Optional::orElseThrow)
                .collect(Collectors.toList());
    }
}
