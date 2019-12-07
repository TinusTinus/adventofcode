package nl.mvdr.adventofcode.adventofcode2019.day06;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

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
        Map<String, CelestialObject> objects = new HashMap<>();
        
        lines.filter(Predicate.not(String::isBlank))
                .forEach(line -> {
                    String[] parts = line.split("\\)");
                    CelestialObject lhs = objects.computeIfAbsent(parts[0], name -> new CelestialObject());
                    CelestialObject rhs = objects.computeIfAbsent(parts[1], name -> new CelestialObject());
                    rhs.setOrbitedObject(lhs);
                });
        
        return objects.values().stream()
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
