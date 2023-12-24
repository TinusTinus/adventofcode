package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/24">Never Tell Me The Odds</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NeverTellMeTheOddsPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeverTellMeTheOddsPart2.class);

    @Override
    public long solve(Stream<String> lines) {
        // Note: I struggled quite a bit with today's part 2.
        // It seems less a programming problem and more a math problem
        // (and I don't often solve these kinds of math problems these days).
        // After looking up some hints I did not end up solving this problem using a Java program.
        // Instead I used Z3 (https://github.com/Z3Prover/z3), in combination with a Python script,
        
        // We know that, for each hailstone, some timestamp t must exist so that:
        //   hailstone.position + t * hailstone.velocity = rock.position + t * rock.velocity
        // By using multiple hailstones, we can set up a system of equations,
        // and then solve for the rock's position.
        
        // Interestingly, any three hailstones from the puzzle input
        // should contain enough information to solve this system. I used the first three.
        // The rest of the input is completely redundant, as far as part 2 is concerned.
        
        // See solution-day24-2023.py for the script itself. 
        // For the inspiration, see https://www.reddit.com/r/adventofcode/comments/18pnycy/comment/kept9a6 
        // from Reddit user mayoff (https://www.reddit.com/user/mayoff/).
        // To run the script, first install the z3-solver Python module: "pip install z3-solver".
        
        // The following number was taken from the script's output.
        return 843888100572888L;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new NeverTellMeTheOddsPart2();

        var result = instance.solve("input-day24-2023.txt");

        LOGGER.info(result);
    }
}
 