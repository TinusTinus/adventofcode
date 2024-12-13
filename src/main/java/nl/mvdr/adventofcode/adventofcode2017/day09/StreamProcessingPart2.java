package nl.mvdr.adventofcode.adventofcode2017.day09;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 9 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/9">Stream Processing</a>.
 *
 * @author Martijn van de Rijdt
 */
public class StreamProcessingPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamProcessingPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return non-canceled garbage characters
     */
    @Override
    public int solve(Stream<String> lines) {
        String inputText = lines.findFirst().orElseThrow();
        
        LOGGER.debug("Input: {}", inputText);
        
        boolean canceled = false;
        boolean garbage = false;
        int garbageCount = 0;
        
        for (int i = 0; i != inputText.length(); i++) {
            char c = inputText.charAt(i);
            
            if (canceled) {
                LOGGER.debug(c + ": ignoring canceled character.");
                canceled = false;
            } else if (c == '!') {
                LOGGER.debug("!: canceling next character.");
                canceled = true;
            } else if (garbage && c == '>') {
                LOGGER.debug(">: end of garbage.");
                garbage = false;
            } else if (garbage) {
                LOGGER.debug(c + ": garbage.");
                garbageCount++;
            } else if (c == '<') {
                LOGGER.debug("<: start of garbage.");
                garbage = true;
            } else {
                LOGGER.debug(c + ": ignoring group-related character.");
            }
        }
        
        return garbageCount;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        StreamProcessingPart2 instance = new StreamProcessingPart2();

        String result = instance.solve("input-day09-2017.txt");

        LOGGER.info(result);
    }
}
