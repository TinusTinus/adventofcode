package nl.mvdr.adventofcode.adventofcode2016.day03;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 3 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/3">Squares With Three Sides</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SquaresPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SquaresPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of possible triangles
     */
    @Override
    public int solve(Stream<String> input) {
        List<String> lines = input.collect(Collectors.toList());
        
        int result = 0;
        
        for (int i = 0; i < lines.size(); i = i + 3) {
            String[] parts0 = lines.get(i).trim().split(" +");
            String[] parts1 = lines.get(i + 1).trim().split(" +");
            String[] parts2 = lines.get(i + 2).trim().split(" +");
            
            for (int j = 0; j != 3; j++) {
                int a = Integer.parseInt(parts0[j]);
                int b = Integer.parseInt(parts1[j]);
                int c = Integer.parseInt(parts2[j]);
                Triangle triangle = new Triangle(a, b, c);
                if (triangle.isPossible()) {
                    result++;
                }
            }
        }
        
        return result;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SquaresPart2 instance = new SquaresPart2();

        String result = instance.solve("input-day03-2016.txt");

        LOGGER.info(result);
    }
}
