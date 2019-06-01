package nl.mvdr.adventofcode.adventofcode2018.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to part 1 of the day 12 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/12">Subterranean Sustainability</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SubterraneanSustainabilityPart1 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SubterraneanSustainabilityPart1.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
                // ignore empty lines
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
        
        State state = State.parseInitial(lines.get(0));
        LOGGER.debug("Initial state: {}", state);
        
        Set<Note> notes = lines.subList(1, lines.size())
                .stream()
                .map(Note::parse)
                .collect(Collectors.toSet());
        LOGGER.debug("Notes: {}", notes);
        
        for (int i = 0; i != 20; i++) {
            state = state.nextGeneration(notes);
            LOGGER.debug("State: {}", state);
        }
        
        return state.getValue() + "";
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SubterraneanSustainabilityPart1 instance = new SubterraneanSustainabilityPart1();

        String solution = instance.solve("input-day12-2018.txt");
        
        LOGGER.info(solution);
    }
}
