package nl.mvdr.adventofcode.adventofcode2022.day05;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A single step in the rearrangement procedure.
 *
 * @author Martijn van de Rijdt
 */
record RearrangementProcedureStep(int numberOfCrates, int sourceStack, int targetStack) {
    
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
}
