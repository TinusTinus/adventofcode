package nl.mvdr.adventofcode.adventofcode2017.day24;

import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 24 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/24">Electromagnetic Moat</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ElectromagneticMoatPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElectromagneticMoatPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return strength of the strongest bridge
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Component> components = Component.parse(lines);
        return Bridge.validBridges(components)
                .stream()
                .mapToInt(Bridge::strength)
                .max()
                .getAsInt();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ElectromagneticMoatPart1 instance = new ElectromagneticMoatPart1();

        String result = instance.solve("input-day24-2017.txt");

        LOGGER.info(result);
    }
}
