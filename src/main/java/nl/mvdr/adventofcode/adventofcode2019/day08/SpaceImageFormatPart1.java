package nl.mvdr.adventofcode.adventofcode2019.day08;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 8 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/8">Space Image Format</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceImageFormatPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceImageFormatPart1.class);

    private final int width;
    private final int height;
    
    /** Constructor. */
    public SpaceImageFormatPart1() {
        this(25, 6);
    }
    
    /**
     * Constructor.
     * 
     * @param width width of each layer, in pixels
     * @param height height of each layer, in pixels
     */
    SpaceImageFormatPart1(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return the number of 1 digits multiplied by the number of 2 digits, for the
     *         layer that contains the fewest 0 digits
     */
    @Override
    public long solve(Stream<String> lines) {
        List<Integer> digits = lines.findFirst().orElseThrow()
                .chars()
                .mapToObj(c -> Character.toString((char)c))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        List<List<Integer>> layers = ListUtils.partition(digits, width * height);

        Comparator<List<Integer>> comparator = Comparator.comparingLong(layer -> countOccurrences(layer, 0));
        
        List<Integer> layer = layers.stream()
                .min(comparator)
                .orElseThrow();
                
        return countOccurrences(layer, 1) * countOccurrences(layer, 2);
    }
    
    /**
     * Counts the occurrences of the provided value in the given list.
     * 
     * @param list list to search
     * @param value value to search for
     * @return number of occurrences in the given list
     */
    private long countOccurrences(List<Integer> list, int value) {
        return list.stream()
                .filter(i -> i.intValue() == value)
                .count();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpaceImageFormatPart1 instance = new SpaceImageFormatPart1();

        String result = instance.solve("input-day08-2019.txt");

        LOGGER.info(result);
    }
}
