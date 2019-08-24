package nl.mvdr.adventofcode.adventofcode2017.day10;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 10 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/10">Knot Hash</a>.
 *
 * @author Martijn van de Rijdt
 */
public class KnotHashPart2 extends KnotHash<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnotHashPart2.class);
    
    /** Constructor. */
    public KnotHashPart2() {
        super();
    }
    
    @Override
    List<Integer> inputLengths(String inputText) {
        List<Integer> result = inputText.chars()
                .boxed()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        
        result.add(Integer.valueOf(17));
        result.add(Integer.valueOf(31));
        result.add(Integer.valueOf(73));
        result.add(Integer.valueOf(47));
        result.add(Integer.valueOf(23));
        
        return result;
    }

    @Override
    int rounds() {
        return 64;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return hash value
     */
    @Override
    String solve(Deque<Integer> sparseHash) {
        List<Integer> sparseHashAsList = new ArrayList<>(sparseHash);
        return IntStream.range(0, 16)
                .mapToObj(i -> sparseHashAsList.subList(i * 16, (i + 1) * 16))
                .map(this::processBlock)
                .collect(Collectors.joining());
    }
    
    private String processBlock(List<Integer> block) {
        int xord = block.stream()
                .mapToInt(Integer::intValue)
                .reduce((i, j) -> i ^ j)
                .getAsInt();
        
        String result = Integer.toHexString(xord);
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        KnotHashPart2 instance = new KnotHashPart2();

        String result = instance.solve("input-day10-2017.txt");

        LOGGER.info(result);
    }
}
