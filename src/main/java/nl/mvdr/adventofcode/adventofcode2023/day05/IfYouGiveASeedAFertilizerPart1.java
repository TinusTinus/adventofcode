package nl.mvdr.adventofcode.adventofcode2023.day05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.Solver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/5">If You Give A Seed A Fertilizer</a>.
 *
 * @author Martijn van de Rijdt
 */
public class IfYouGiveASeedAFertilizerPart1 implements Solver {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfYouGiveASeedAFertilizerPart1.class);

    @Override
    public String solve(String inputfile) {
        return new IfYouGiveASeedAFertilizer(true).solve(inputfile);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new IfYouGiveASeedAFertilizerPart1();

        var result = instance.solve("input-day05-2023.txt");

        LOGGER.info(result);
    }
}
 