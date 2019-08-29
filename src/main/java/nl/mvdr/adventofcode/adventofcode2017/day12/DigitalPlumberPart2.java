package nl.mvdr.adventofcode.adventofcode2017.day12;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 12 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/12">Digital Plumber</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DigitalPlumberPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DigitalPlumberPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of groups
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Line> lines = Line.parse(inputFilePath);
        
        LOGGER.debug("Lines: {}", lines);
        
        Set<Set<Integer>> groups = new HashSet<>();
        
        for (Line line : lines) {
            Set<Integer> programIds = line.programIds();
            
            Set<Integer> newGroup = new HashSet<>(programIds);
            
            for (Integer programId : programIds) {
                groups.stream()
                        .filter(group -> group.contains(programId))
                        .findFirst()
                        .ifPresent(oldGroup -> {
                            groups.remove(oldGroup);
                            newGroup.addAll(oldGroup);
                        });
            }
            
            groups.add(newGroup);
        }
        
        return Integer.valueOf(groups.size());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DigitalPlumberPart2 instance = new DigitalPlumberPart2();

        String result = instance.solve("input-day12-2017.txt");

        LOGGER.info(result);
    }
}
