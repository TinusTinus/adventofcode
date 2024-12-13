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
public class StreamProcessingPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamProcessingPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return score
     */
    @Override
    public int solve(Stream<String> lines) {
        String inputText = lines.findFirst().orElseThrow();
        
        LOGGER.debug("Input: {}", inputText);
        
        int currentGroupScore = 0;
        int totalScore = 0;
        boolean canceled = false;
        boolean garbage = false;
        
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
                LOGGER.debug(c + ": ignoring garbage.");
            } else if (c == '<') {
                LOGGER.debug("<: start of garbage.");
                garbage = true;
            } else if (c == '{') {
                LOGGER.debug("{: start of new group.");
                currentGroupScore++;
            } else if (c == '}') {
                LOGGER.debug("}: end of group.");
                totalScore = totalScore + currentGroupScore;
                currentGroupScore--;
            } else if (c == ',') {
                LOGGER.debug(",: comma between groups.");
            } else {
                throw new IllegalStateException("Unexpected character " + c + " in position " + i);
            }
        }
        
        return totalScore;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        StreamProcessingPart1 instance = new StreamProcessingPart1();

        String result = instance.solve("input-day09-2017.txt");

        LOGGER.info(result);
    }
}
