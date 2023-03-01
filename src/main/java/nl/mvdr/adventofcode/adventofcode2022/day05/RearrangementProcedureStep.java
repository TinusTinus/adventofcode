package nl.mvdr.adventofcode.adventofcode2022.day05;

import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A single step in the rearrangement procedure.
 *
 * @author Martijn van de Rijdt
 */
record RearrangementProcedureStep(int numberOfCrates, int sourceStack, int targetStack) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RearrangementProcedureStep.class);
    
    /**
     * Parses a single line from the puzzle input.
     * 
     * @param line textual representation of a step; for example: "move 3 from 1 to 3"
     * @return step
     */
    static RearrangementProcedureStep parse(String line) {
        Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (!matcher.matches() || matcher.groupCount() != 3) {
            throw new IllegalArgumentException("Unable to parse input: " + line);
        }
        
        int numberOfCrates = Integer.parseInt(matcher.group(1));
        int sourceStack = Integer.parseInt(matcher.group(2));
        int targetStack = Integer.parseInt(matcher.group(3));
        
        return new RearrangementProcedureStep(numberOfCrates, sourceStack, targetStack);
    }
    
    /**
     * Performs this step.
     * 
     * @param stacks the stacks of crates to be updated
     */
    void perform(List<Deque<Character>> stacks) {
        LOGGER.debug("Performing step: {}", this);
        IntStream.range(0, numberOfCrates)
                .mapToObj(i -> stacks.get(sourceStack - 1).pop())
                .peek(crate -> LOGGER.debug("Moving crate {} from stack {} to stack {}", crate, Integer.valueOf(sourceStack), Integer.valueOf(targetStack)))
                .forEach(stacks.get(targetStack - 1)::push);
        
        LOGGER.debug("Updated stacks: {}", stacks);
    }
}
