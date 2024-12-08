package nl.mvdr.adventofcode.adventofcode2019.day06;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 6 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/6">Universal Orbit Map</a>.
 *
 * @author Martijn van de Rijdt
 */
public class UniversalOrbitMapPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniversalOrbitMapPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return total number of orbits
     */
    @Override
    public int solve(Stream<String> lines) {
        return CelestialObject.parse(lines)
                .values()
                .stream()
                .mapToInt(CelestialObject::computeTotalOrbits)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        UniversalOrbitMapPart1 instance = new UniversalOrbitMapPart1();

        String result = instance.solve("input-day06-2019.txt");

        LOGGER.info(result);
    }
}
