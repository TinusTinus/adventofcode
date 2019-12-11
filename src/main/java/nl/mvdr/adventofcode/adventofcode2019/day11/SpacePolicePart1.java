package nl.mvdr.adventofcode.adventofcode2019.day11;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 11 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/11">Space Police</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpacePolicePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpacePolicePart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return how many panels the robot paints at least once
     */
    @Override
    public int solve(Stream<String> lines) {
        Ship ship = new Ship(false);
        
        Program program = Program.parse(lines.findFirst().orElseThrow(), ship, ship);
        
        program.execute();
        
        return ship.getHull().numberOfPaintedPanels();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpacePolicePart1 instance = new SpacePolicePart1();

        String result = instance.solve("input-day11-2019.txt");

        LOGGER.info(result);
    }
}
