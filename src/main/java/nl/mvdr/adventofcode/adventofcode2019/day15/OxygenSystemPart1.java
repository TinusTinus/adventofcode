package nl.mvdr.adventofcode.adventofcode2019.day15;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 15 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/15">Oxygen System</a>.
 *
 * @author Martijn van de Rijdt
 */
public class OxygenSystemPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OxygenSystemPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return number of movement commands
     */
    @Override
    public int solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        Location droid = new Location(program)
                .moveToOxygenSystem();
        
        return droid.getPathLength();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        OxygenSystemPart1 instance = new OxygenSystemPart1();

        String result = instance.solve("input-day15-2019.txt");

        LOGGER.info(result);
    }
}
 