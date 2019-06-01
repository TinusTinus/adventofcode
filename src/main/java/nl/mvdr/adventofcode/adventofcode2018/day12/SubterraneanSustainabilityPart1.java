package nl.mvdr.adventofcode.adventofcode2018.day12;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to part 1 of the day 12 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/12">Subterranean Sustainability</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SubterraneanSustainabilityPart1 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SubterraneanSustainabilityPart1.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        return null; // TODO
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SubterraneanSustainabilityPart1 instance = new SubterraneanSustainabilityPart1();

        String solution = instance.solve("input-day09-2018.txt");
        
        LOGGER.info(solution);
    }
}
