package nl.mvdr.adventofcode.adventofcode2019.day08;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to the day 8 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/8">Space Image Format</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceImageFormatPart2 implements LinesSolver<Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceImageFormatPart2.class);

    private final int width;
    private final int height;
    
    /** Constructor. */
    public SpaceImageFormatPart2() {
        this(25, 6);
    }
    
    /**
     * Constructor.
     * 
     * @param width width of each layer, in pixels
     * @param height height of each layer, in pixels
     */
    SpaceImageFormatPart2(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return {@code null}; inspect the log for the puzzle answer
     */
    @Override
    public Void solve(Stream<String> lines) {
        List<Integer> digits = lines.findFirst().orElseThrow()
                .chars()
                .mapToObj(c -> Character.toString((char)c))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        List<List<Integer>> layers = ListUtils.partition(digits, width * height);

        List<Integer> image = IntStream.range(0, width * height)
                .mapToObj(i -> layers.stream()
                    .map(layer -> layer.get(i))
                    // Find the first color which is not 2 (= transparent)
                    .filter(c -> c.intValue() != 2)
                    .findFirst()
                    .get())
                .collect(Collectors.toList());
        
        // Log each row
        ListUtils.partition(image, width)
            .stream()
            .map(this::toString)
            .forEach(LOGGER::info);
        
        return null;
    }
    
    /**
     * Creates a string representation for the given image row.
     * 
     * @param row row, containing only 0 (= black) and 1 (= white)
     * @return string representation of the given row
     */
    private String toString(List<Integer> row) {
        StringBuilder result = new StringBuilder();
        
        row.stream()
            // Represent black as " " and white as "#"
            .map(i -> i.intValue() == 0 ? " " : "#")
            .forEach(result::append);
        
        return result.toString();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpaceImageFormatPart2 instance = new SpaceImageFormatPart2();

        instance.solve("input-day08-2019.txt");
    }
}
