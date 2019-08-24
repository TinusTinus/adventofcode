package nl.mvdr.adventofcode.adventofcode2017.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 10 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/10">Knot Hash</a>.
 *
 * @author Martijn van de Rijdt
 */
public class KnotHashPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnotHashPart1.class);
    
    private final int listSize;
    
    /** Constructor. */
    public KnotHashPart1() {
        super();
        this.listSize = 255;
    }
    
    /**
     * Constructor.
     * 
     * @param listSize list size
     */
    KnotHashPart1(int listSize) {
        super();
        this.listSize = listSize;
    }

    /**
     * {@inheritDoc}
     * 
     * @return the result of multiplying the first two numbers in the list, after applying the knot hash
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        String inputText = Files.lines(inputFilePath).findFirst().get();
        List<Integer> inputLengths = Stream.of(inputText.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        Deque<Integer> numbers = IntStream.range(0, listSize)
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

        // Invariant: the head of the deque is the current position.
        int skipSize = 0;
        for (Integer inputLength : inputLengths) {
            // Reverse the first inputLength elements of the numbers.
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i != inputLength; i++) {
                temp.add(numbers.pollFirst());
            }
            Collections.reverse(temp);
            temp.forEach(numbers::offerFirst);
            
            // TODO more stuff
        }
        
        return null; // TODO
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
