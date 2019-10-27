package nl.mvdr.adventofcode.adventofcode2017.day24;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 24 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/24">Electromagnetic Moat</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ElectromagneticMoatPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElectromagneticMoatPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return strength of the longest bridge
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Set<Component> components = Component.parse(inputFilePath);
        Set<Bridge> validBridges = Bridge.validBridges(components);
        int strength = validBridges.stream()
                .max(Comparator.comparing(Bridge::length).thenComparing(Bridge::strength))
                .get()
                .strength();
        return Integer.valueOf(strength);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ElectromagneticMoatPart2 instance = new ElectromagneticMoatPart2();

        String result = instance.solve("input-day24-2017.txt");

        LOGGER.info(result);
    }
}
