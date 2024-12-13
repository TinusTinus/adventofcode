package nl.mvdr.adventofcode.adventofcode2018.day05;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 5 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/5">Alchemical Reduction</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AlchemicalReductionPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlchemicalReductionPart1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        return lines.findFirst()
                .map(Polymers::react)
                .get()
                .length();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AlchemicalReductionPart1 instance = new AlchemicalReductionPart1();

        String result = instance.solve("input-day05-2018.txt");

        LOGGER.info(result);
    }
}
