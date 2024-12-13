package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/19">Aplenty</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AplentyPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AplentyPart1.class);

    @Override
    public int solve(Stream<String> linesStream) {
        List<String> lines = linesStream.toList();
        var emptyLineIndex = lines.indexOf("");
        
        var workflows = Workflow.parse(lines.subList(0, emptyLineIndex));
        
        var parts = Part.parse(lines.subList(emptyLineIndex + 1, lines.size()));
        
        var in = workflows.get("in");
        
        return parts.stream()
                .filter(part -> in.accepts(part, workflows))
                .mapToInt(Part::totalRatings)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new AplentyPart1();

        var result = instance.solve("input-day19-2023.txt");

        LOGGER.info(result);
    }
}
 