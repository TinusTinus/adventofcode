package nl.mvdr.adventofcode.adventofcode2018.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 2 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/5">Alchemical Reduction</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReduction implements PathSolver {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        String polymer = Files.lines(inputFilePath)
                .findFirst()
                .get();
        
        return "" + reduce(polymer).length();
    }
    
    private String reduce(String polymer) {
        String result = polymer;
        
        boolean done = false;
        while (!done) {
            int i = 1;
            while (i != result.length() && !canReact(result.charAt(i - 1), result.charAt(i))) {
                i++;
            }
            
            if (i == result.length()) {
                done = true;
            } else {
                // A reaction is possible.
                result = result.substring(0, i - 1) + result.substring(i + 1);
            }
        }
        
        return result;
    }
    
    private boolean canReact(char c0, char c1) {
        // must have the same type
        return Character.toLowerCase(c0) == Character.toLowerCase(c1)
                // and opposing polarities
                && Character.isUpperCase(c0) != Character.isUpperCase(c1);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AlchemicalReduction instance = new AlchemicalReduction();

        String result = instance.solve("input-day05-2018.txt");

        System.out.println(result);
    }
}
