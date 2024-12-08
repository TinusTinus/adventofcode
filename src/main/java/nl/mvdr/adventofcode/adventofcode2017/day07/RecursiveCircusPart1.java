package nl.mvdr.adventofcode.adventofcode2017.day07;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to the day 7 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/7">Recursive Circus</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RecursiveCircusPart1 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecursiveCircusPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return name of the bottom program
     */
    @Override
    public String solve(Stream<String> lines) {
        List<Program> programs = Program.parse(lines);
        
        LOGGER.debug("Programs: {}", programs);
        
        return Tower.determineBase(programs).getName();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RecursiveCircusPart1 instance = new RecursiveCircusPart1();

        String result = instance.solve("input-day07-2017.txt");

        LOGGER.info(result);
    }
}
