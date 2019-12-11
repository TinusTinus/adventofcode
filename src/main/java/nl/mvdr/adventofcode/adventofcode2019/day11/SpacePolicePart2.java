package nl.mvdr.adventofcode.adventofcode2019.day11;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 11 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/11">Space Police</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpacePolicePart2 implements LinesSolver<Hull> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpacePolicePart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return multi-line string representation of the hull
     */
    @Override
    public Hull solve(Stream<String> lines) {
        Ship ship = new Ship(true);
        
        Program program = Program.parse(lines.findFirst().orElseThrow(), ship, ship);
        
        program.execute();
        
        return ship.getHull();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpacePolicePart2 instance = new SpacePolicePart2();

        String result = instance.solve("input-day11-2019.txt");

        LOGGER.info(result);
    }
}
