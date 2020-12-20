package nl.mvdr.adventofcode.adventofcode2020.day12;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 12 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/12">Rain Risk</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RainRiskPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RainRiskPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return Manhattan distance between the start and end position of the ship
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Instruction> instructions = Instruction.parse(lines, false);
        
        Ship ship = Ship.STARTING_POSITION;
        
        for (Instruction instruction : instructions) {
            ship = instruction.execute(ship);
        }
        
        return ship.location().manhattanDistanceToOrigin();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RainRiskPart2 instance = new RainRiskPart2();

        String result = instance.solve("input-day12-2020.txt");

        LOGGER.info(result);
    }
}
 