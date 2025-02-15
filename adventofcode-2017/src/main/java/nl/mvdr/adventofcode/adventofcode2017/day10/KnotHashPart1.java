package nl.mvdr.adventofcode.adventofcode2017.day10;

import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 10 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/10">Knot Hash</a>.
 *
 * @author Martijn van de Rijdt
 */
public class KnotHashPart1 extends KnotHash<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnotHashPart1.class);
    
    /** Constructor. */
    public KnotHashPart1() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param listSize list size
     */
    KnotHashPart1(int listSize) {
        super(listSize);
    }

    @Override
    List<Integer> inputLengths(String inputText) {
        return Stream.of(inputText.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    int rounds() {
        return 1;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return the result of multiplying the first two numbers in the list, after applying the knot hash
     */
    @Override
    Integer solve(Deque<Integer> sparseHash) {
        return Integer.valueOf(sparseHash.pollFirst().intValue() * sparseHash.pollFirst().intValue());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        KnotHashPart1 instance = new KnotHashPart1();

        String result = instance.solve("input-day10-2017.txt");

        LOGGER.info(result);
    }
}
