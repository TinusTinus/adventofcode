package nl.mvdr.adventofcode.adventofcode2022.day11;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/11">Monkey in the Middel</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyInTheMiddlePart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyInTheMiddlePart2.class);
    
    @Override
    public long solve(Stream<String> lines) {
        var state = State.init(lines, false);
        
        state = state.performRounds(10_000);
        
        return state.calculateMonkeyBusiness();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new MonkeyInTheMiddlePart2();

        var result = instance.solve("input-day11-2022.txt");

        LOGGER.info(result);
    }
}
 