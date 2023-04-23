package nl.mvdr.adventofcode.adventofcode2022.day11;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/11">Monkey in the Middel</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyInTheMiddlePart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyInTheMiddlePart1.class);
    
    @Override
    public long solve(Stream<String> lines) {
        var state = State.parse(lines, true);
        
        state = state.performRounds(20);
        
        return state.calculateMonkeyBusiness();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new MonkeyInTheMiddlePart1();

        var result = instance.solve("input-day11-2022.txt");

        LOGGER.info(result);
    }
}
 