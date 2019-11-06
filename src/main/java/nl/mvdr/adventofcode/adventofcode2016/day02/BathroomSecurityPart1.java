package nl.mvdr.adventofcode.adventofcode2016.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 2 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/2">Bathroom Security</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BathroomSecurityPart1 implements PathSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BathroomSecurityPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return keypad's security code
     */
    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<List<Direction>> instructions = Files.lines(inputFilePath)
                .filter(Predicate.not(String::isEmpty))
                .map(this::parse)
                .collect(Collectors.toList());
        
        // Keypad:
        // 1 2 3
        // 4 5 6
        // 7 8 9
        Map<Point, String> keypad = Map.of(
                new Point(0, 0), "1",
                new Point(1, 0), "2",
                new Point(2, 0), "3",
                new Point(0, 1), "4",
                new Point(1, 1), "5",
                new Point(2, 1), "6",
                new Point(0, 2), "7",
                new Point(1, 2), "8",
                new Point(2, 2), "9"
                );
        // Start at "5"
        Point currentKey = new Point(1, 1);
        
        String result = "";
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
            result = result + value;
        }
        
        return result;
    }
    
    private List<Direction> parse(String line) {
        return line.chars()
                .mapToObj(c -> Direction.of((char)c))
                .map(Optional::get)
                .collect(Collectors.toList());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        BathroomSecurityPart1 instance = new BathroomSecurityPart1();

        String result = instance.solve("input-day02-2016.txt");

        LOGGER.info(result);
    }
}
