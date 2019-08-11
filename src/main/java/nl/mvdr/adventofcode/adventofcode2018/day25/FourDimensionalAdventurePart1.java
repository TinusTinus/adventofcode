package nl.mvdr.adventofcode.adventofcode2018.day25;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 25 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/25">Four-Dimensional Adventure</a>.
 *
 * @author Martijn van de Rijdt
 */
public class FourDimensionalAdventurePart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FourDimensionalAdventurePart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return number of constellations
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Set<Point4D> points = Point4D.parse(inputFilePath);
        
        Set<Set<Point4D>> constellations = new HashSet<>();
        
        points.forEach(point -> {
            Set<Set<Point4D>> matchingConstellations = constellations.stream()
                    .filter(point::inRange)
                    .collect(Collectors.toSet());
            
            // Remove any and all matching constellations.
            constellations.removeAll(matchingConstellations);
            
            // Insert the new constellation (replacing any that were just removed).
            Set<Point4D> newConstellation;
            newConstellation = new HashSet<>();
            matchingConstellations.stream()
                    .flatMap(Set::stream)
                    .forEach(newConstellation::add);
            newConstellation.add(point);
            constellations.add(newConstellation);
        });
        
        return constellations.size();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        FourDimensionalAdventurePart1 solver = new FourDimensionalAdventurePart1();
        String solution = solver.solve("input-day25-2018.txt");
        LOGGER.info(solution);
    }
}
