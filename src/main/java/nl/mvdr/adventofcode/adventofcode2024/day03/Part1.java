package nl.mvdr.adventofcode.adventofcode2024.day03;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> lines) {
        return lines.mapToInt(this::solveLine).sum();
    }

    private int solveLine(String line) {
        String remainingInput = line;
        
        int result = 0;
        
        while (!"".equals(remainingInput)) {
            int indexOfStart = remainingInput.indexOf("mul(");
            if (indexOfStart < 0) {
                // No more matches
                remainingInput = "";
            } else {
                remainingInput = remainingInput.substring(indexOfStart + 4);
                
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
                        result = result + lhs * rhs;
                        remainingInput = remainingInput.substring(indexOfEnd + 1);
                    }
                }
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day03-2024.txt");

        LOGGER.info(result);
    }
}
 