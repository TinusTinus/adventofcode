package nl.mvdr.adventofcode.adventofcode2024.day03;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

public class MullItOverSolver implements IntSolver {

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
            
            if (remainingInput.startsWith("do()")) {
                mulEnabled = true;
                remainingInput = remainingInput.substring(4);
            } else if (remainingInput.startsWith("don't()")) {
                mulEnabled = !processConditionals;
                remainingInput = remainingInput.substring(7);
            } else if (mulEnabled && remainingInput.startsWith("mul(")) {
                int indexOfEnd = remainingInput.indexOf(")");
                
                if (indexOfEnd < 0) {
                    // No more matches
                    remainingInput = "";
                } else {
                    String parametersString = remainingInput.substring(4, indexOfEnd);
                    String[] parts = parametersString.split(",");
                    if (parts.length == 2 && Stream.of(parts).allMatch(part -> part.matches("\\d{1,3}"))) {
                        // Found a mul instruction
                        int lhs = Integer.parseInt(parts[0]);
                        int rhs = Integer.parseInt(parts[1]);
                        result = result + lhs * rhs;
                        remainingInput = remainingInput.substring(indexOfEnd + 1);
                    } else {
                        remainingInput = remainingInput.substring(4);
                    }
                }
            } else {
                remainingInput = remainingInput.substring(1);
            }
        }
        
        return result;
    }
}
 