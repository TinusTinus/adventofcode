package nl.mvdr.adventofcode.adventofcode2018.day12;

import java.io.IOException;
import java.math.BigDecimal;
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
 * Solution to the day 12 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/12">Subterranean Sustainability</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SubterraneanSustainability implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SubterraneanSustainability.class);
    
    private final BigDecimal generations;
    
    /** Constructor, for solving with 20 generations. */
    public SubterraneanSustainability() {
        // 20, as in the example and Part 1
        this(BigDecimal.valueOf(20));
    }
    
    /**
     * Constructor.
     * 
     * @param generations number of generations
     */
    private SubterraneanSustainability(BigDecimal generations) {
        super();
        this.generations = generations;
    }
    
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
        
        for (BigDecimal i = BigDecimal.ZERO; !i.equals(generations); i = i.add(BigDecimal.ONE)) {
            state = state.nextGeneration(notes);
            LOGGER.debug("State: {}", state);
            
            if (1 < i.compareTo(BigDecimal.ZERO) && i.divide(BigDecimal.valueOf(1_000_000L)).equals(BigDecimal.ZERO)) {
                LOGGER.info("Completed {} iterations.", i);
            }
        }
        
        return state.getValue() + "";
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SubterraneanSustainability solverPart1 = new SubterraneanSustainability(BigDecimal.valueOf(20));
        String solutionPart1 = solverPart1.solve("input-day12-2018.txt");
        LOGGER.info("Part 1: {}", solutionPart1);
        
        BigDecimal fiftyBillion = BigDecimal.valueOf(5_000_000_000L).multiply(BigDecimal.valueOf(10));
        SubterraneanSustainability solverPart2 = new SubterraneanSustainability(fiftyBillion);
        String solutionPart2 = solverPart2.solve("input-day12-2018.txt");
        LOGGER.info("Part 2: {}", solutionPart2);
    }
}
