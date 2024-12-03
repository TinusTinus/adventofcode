package nl.mvdr.adventofcode.adventofcode2024.day03;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

public class MullItOverSolver implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MullItOverSolver.class);

    private final boolean processConditionals;
    
    public MullItOverSolver(boolean processConditionals) {
        this.processConditionals = processConditionals;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        String remainingInput = lines.collect(Collectors.joining());
        
        int result = 0;
        boolean mulEnabled = true;
        
        while (!"".equals(remainingInput)) {
            
            if (mulEnabled && remainingInput.startsWith("mul(")) {
                remainingInput = remainingInput.substring(4);
                
                int indexOfEnd = remainingInput.indexOf(")");
                
                if (indexOfEnd < 0) {
                    // No more matches
                    remainingInput = "";
                } else {
                    String parametersString = remainingInput.substring(0, indexOfEnd);
                    String[] parts = parametersString.split(",");
                    if (parts.length == 2 && Stream.of(parts).allMatch(part -> part.matches("\\d{1,3}"))) {
                        // Found a mul instruction
                        int lhs = Integer.parseInt(parts[0]);
                        int rhs = Integer.parseInt(parts[1]);
                        LOGGER.debug("mul! {} * {}", Integer.valueOf(lhs), Integer.valueOf(rhs));
                        result = result + lhs * rhs;
                        remainingInput = remainingInput.substring(indexOfEnd + 1);
                    }
                }
            } else if (processConditionals && remainingInput.startsWith("do()")) {
                LOGGER.debug("do!");
                mulEnabled = true;
                remainingInput = remainingInput.substring(4);
            } else if (processConditionals && remainingInput.startsWith("don't()")) {
                LOGGER.debug("don't!");
                mulEnabled = false;
                remainingInput = remainingInput.substring(7);
            } else {
                remainingInput = remainingInput.substring(1);
            }
        }
        
        return result;
    }
}
 