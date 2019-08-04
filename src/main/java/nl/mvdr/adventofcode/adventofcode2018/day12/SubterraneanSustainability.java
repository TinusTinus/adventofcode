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
public class SubterraneanSustainability implements PathSolver<Integer> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SubterraneanSustainability.class);
    
    private final int generations;
    
    /** Constructor, for solving with 20 generations. */
    public SubterraneanSustainability() {
        // 20, as in the example and Part 1
        this(20);
    }
    
    /**
     * Constructor.
     * 
     * @param generations number of generations
     */
    private SubterraneanSustainability(int generations) {
        super();
        this.generations = generations;
    }
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
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
        
        for (int i = 0; i != generations; i++) {
            state = state.nextGeneration(notes);
            LOGGER.debug("State: {}", state);
        }
        
        return Integer.valueOf(state.getValue());
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        
        // --- Part 1 ---
        SubterraneanSustainability solverPart1 = new SubterraneanSustainability();
        String solutionPart1 = solverPart1.solve("input-day12-2018.txt");
        LOGGER.info("Part 1 solution: {}", solutionPart1);

        // --- Part 2 ---
        // Note: eventually the puzzle reaches a point where the plants all shift one position to the right in each generation.
        // This results in a value increase of 42 for each generation.
        // This starts happening sometime before 1000 iterations.
        
        SubterraneanSustainability solverPart2 = new SubterraneanSustainability(1_000);
        int stateValue1000 = Integer.parseInt(solverPart2.solve("input-day12-2018.txt"));
        
        BigDecimal fiftyBillion = BigDecimal.valueOf(5_000_000_000L).multiply(BigDecimal.valueOf(10));
        BigDecimal remainingGenerations = fiftyBillion.subtract(BigDecimal.valueOf(1000L));
        BigDecimal remainingGenerationValues = remainingGenerations.multiply(BigDecimal.valueOf(42L));
        BigDecimal solutionPart2 = remainingGenerationValues.add(BigDecimal.valueOf(stateValue1000));
        LOGGER.info("Part 2 solution: {}", solutionPart2);
    }
}
