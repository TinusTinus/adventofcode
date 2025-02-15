package nl.mvdr.adventofcode.adventofcode2022.day06;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/06">Tuning Trouble</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TuningTroublePart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TuningTroublePart1.class);

    private static final int SEQUENCE_LENGTH = 4;
    
    @Override
    public long solve(Stream<String> lines) {
        var solver = new TuningTrouble(SEQUENCE_LENGTH);
        return solver.solve(lines);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new TuningTroublePart1();

        var result = instance.solve("input-day06-2022.txt");

        LOGGER.info(result);
    }
}
 